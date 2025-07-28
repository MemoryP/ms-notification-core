package com.ocbc.ms.notification.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.ms.cbs.core.context.LegalEntityContext;
import com.ocbc.ms.cbs.core.dto.CommonResult;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.constant.Constants;
import com.ocbc.ms.notification.core.entity.ServiceCodeTemplateEntity;
import com.ocbc.ms.notification.core.entity.dto.ConsumerDto;
import com.ocbc.ms.notification.core.entity.dto.CustomerMessageDto;
import com.ocbc.ms.notification.core.entity.dto.ProducerRegDto;
import com.ocbc.ms.notification.core.entity.req.EventMessageVO;
import com.ocbc.ms.notification.core.entity.req.staff.StaffNotificationDataDto;
import com.ocbc.ms.notification.core.entity.req.staff.StaffPayLoadDto;
import com.ocbc.ms.notification.core.entity.req.staff.StaffProducerDto;
import com.ocbc.ms.notification.core.entity.req.staff.StaffRecipientInfoDto;
import com.ocbc.ms.notification.core.enums.ExceptionEnum;
import com.ocbc.ms.notification.core.enums.NotificationTypeEnum;
import com.ocbc.ms.notification.core.feign.DTO.FeignRequestHeader;
import com.ocbc.ms.notification.core.feign.DTO.customer.*;
import com.ocbc.ms.notification.core.feign.DTO.param.BasAcctAcoDto;
import com.ocbc.ms.notification.core.feign.DTO.param.OperatorEnum;
import com.ocbc.ms.notification.core.feign.DTO.param.ParameterCodeRequest;
import com.ocbc.ms.notification.core.feign.DTO.param.ParameterCodeRequestBuilder;
import com.ocbc.ms.notification.core.feign.DTO.security.StaffContactRequest;
import com.ocbc.ms.notification.core.feign.DTO.security.StaffContactResponse;
import com.ocbc.ms.notification.core.feign.client.MsCustomerClient;
import com.ocbc.ms.notification.core.feign.client.MsParamClient;
import com.ocbc.ms.notification.core.feign.client.MsSecurityClient;
import com.ocbc.ms.notification.core.producer.LocalKafkaProducer;
import com.ocbc.ms.notification.core.repository.ServiceCodeTemplateRepository;
import com.ocbc.ms.notification.core.runner.TopicRunner;
import com.ocbc.ms.notification.core.service.MessagePkgService;
import com.ocbc.ms.notification.core.service.TbMessageConsumerRegService;
import com.ocbc.ms.notification.core.service.TbMessageProducerRegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessagePkgServiceImpl implements MessagePkgService {

    private final TbMessageConsumerRegService tbMessageConsumerRegServiceImpl;

    private final TbMessageProducerRegService tbMessageProducerRegServiceImpl;

    private final ServiceCodeTemplateRepository serviceCodeTemplateRepository;

    private final LocalKafkaProducer localKafkaProducer;

    private final ObjectMapper objectMapper;

    private final MsCustomerClient msCustomerClient;

    private final MsParamClient msParamClient;

    private final MsSecurityClient msSecurityClient;

    public static final String BAS_ACCT_ACO = "BasAcctAco";

    public static final String ACO_CODE = "acoCode";

    @Value("${spring.kafka.staff.producer.topic}")
    private String staffTopic;

    @Override
    public void sendMessage(String message, String topic){
        try{
            ConsumerDto consumerDto = objectMapper.readValue(message, ConsumerDto.class);
            LegalEntityContext.setLegalEntity(consumerDto.getLegalEntity());
            String consumerId = tbMessageConsumerRegServiceImpl.saveTbMessageConsumerReg(consumerDto);
            Optional<ServiceCodeTemplateEntity> serviceCodeTemplate = serviceCodeTemplateRepository.findById(consumerDto.getServiceCode());

            if (!serviceCodeTemplate.isPresent()) {
                log.warn("Service code template not found for code: {}", consumerDto.getServiceCode());
                return;
            }

            if(TopicRunner.getTopicName(Constants.TOPIC_STAFF).equals(topic)){
                String alterTemplateCode = serviceCodeTemplate.get().getAlterTemplateCode();
                if(StringUtils.isBlank(alterTemplateCode)){
                    log.warn("Field 'ALTER_TEMPLATE_CODE' is empty in service template for code: {}", alterTemplateCode);
                    return;
                }

                List<StaffContactResponse> rmInfo = getRmInfo(consumerDto);

                StaffProducerDto staffProducerMessage = buildStaffMessage(consumerDto, rmInfo, serviceCodeTemplate.get());

                localKafkaProducer.sendMessage(staffTopic, objectMapper.writeValueAsString(staffProducerMessage));

                ProducerRegDto producerRegDto = new ProducerRegDto();
                producerRegDto.setConsumerId(consumerId)
                        .setLegalEntity(consumerDto.getLegalEntity())
                        .setNotificationTopic(topic)
                        .setPayload(objectMapper.writeValueAsString(staffProducerMessage));
                tbMessageProducerRegServiceImpl.saveTbMessageProducerReg(producerRegDto);
            }
            // customer topic
        }catch(Exception e){
            log.info(e.toString());
            throw new BizException(ExceptionEnum.INVALID_JSON);
        }
    }

    public StaffProducerDto buildStaffMessage(ConsumerDto consumerDto, List<StaffContactResponse> staffContactResponse, ServiceCodeTemplateEntity template) {
        return StaffProducerDto.builder()
                .topic(staffTopic)
                .eventMessageVO(EventMessageVO.builder()
                        .itemCode(template.getAlterTemplateCode())
                        .payLoad(StaffPayLoadDto.builder()
                                .txtData(objectMapper.valueToTree(consumerDto))
                                .notificationData(StaffNotificationDataDto.builder()
                                        .channels(Arrays.asList(NotificationTypeEnum.EMAIL.name()))
                                        .recipientInfo(StaffRecipientInfoDto.builder()
                                                .email(staffContactResponse.get(0).getEmail())
                                                .phone(staffContactResponse.get(0).getOtherMobile())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private List<StaffContactResponse> getRmInfo(ConsumerDto consumerDto) throws JsonProcessingException {
        CustomerMessageDto customerMessageDto = parseCustomerMessage(consumerDto);
        FeignRequestHeader header = createRequestHeader(consumerDto);
        CustDetailQueryResponse customerInfo = fetchCustomerInfo(header, customerMessageDto);

        List<String> rmIdList = extractRmIdList(customerInfo);
        BasAcctAcoDto resultList = fetchBasAcctAcoList(header, rmIdList);

        StaffContactRequest staffContactRequest = new StaffContactRequest();
        staffContactRequest.setUserName(resultList.getAcoName());
        // todo 是否只获取primary联系方式
        try {
            return msSecurityClient.getStaffContactList(objectMapper.writeValueAsString(header), staffContactRequest);
        } catch (JsonProcessingException e) {
            log.warn("Failed to serialize header for request", e);
            return Collections.<StaffContactResponse>emptyList();
        }

//        return resultList.stream()
//                .map(basAcctAcoDto -> {
//                    StaffContactRequest staffContactRequest = new StaffContactRequest();
//                    staffContactRequest.setUserName(basAcctAcoDto.getAcoName());
//                    // todo 是否只获取primary联系方式
//                    try {
//                        return msSecurityClient.getStaffContactList(objectMapper.writeValueAsString(header), staffContactRequest);
//                    } catch (JsonProcessingException e) {
//                        log.warn("Failed to serialize header for request", e);
//                        return Collections.<StaffContactResponse>emptyList();
//                    }
//                })
//                .filter(Objects::nonNull)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());

    }

    private CustomerMessageDto parseCustomerMessage(ConsumerDto consumerDto) throws JsonProcessingException {
        return objectMapper.readValue(consumerDto.getMessage(), CustomerMessageDto.class);
    }

    private FeignRequestHeader createRequestHeader(ConsumerDto consumerDto) {
        FeignRequestHeader header = new FeignRequestHeader();
        header.setCorrelationId(consumerDto.getXCorrelationId());
        header.setSourceId(consumerDto.getXSourceId());
        header.setSourceCountry(consumerDto.getXSourceCountry());
        header.setSourceDateTime(String.valueOf(new Date()));
        header.setLegalEntity(consumerDto.getLegalEntity());
        return header;
    }

    private CustDetailQueryResponse fetchCustomerInfo(FeignRequestHeader header, CustomerMessageDto customerMessageDto)
            throws JsonProcessingException {
        String encodedHeader = objectMapper.writeValueAsString(header);
        return msCustomerClient.getCustomerInfo(encodedHeader, customerMessageDto.getCustomerId());
    }

    private List<String> extractRmIdList(CustDetailQueryResponse customerInfo) {
        return Optional.ofNullable(customerInfo)
                .map(CustDetailQueryResponse::getBasicProfile)
                .map(BasicProfile::getCrmInfo)
                .map(CrmInfo::getRmList)
                .orElse(Collections.emptyList())
                .stream()
                // todo BasicProfile下也有rmId？？
                .map(RelationshipManager::getRmId)
                .map(CodeValueInfo::getCodeValue)
                .collect(Collectors.toList());
    }

    private BasAcctAcoDto fetchBasAcctAcoList(FeignRequestHeader header, List<String> rmIdList) throws JsonProcessingException {
        ParameterCodeRequest request = new ParameterCodeRequestBuilder()
                .setCode(BAS_ACCT_ACO)
                .addFilter(ACO_CODE, rmIdList, OperatorEnum.IN.getValue())
                .build();

        CommonResult<List<Object>> commonParameterList = msParamClient.getCommonParameterList(objectMapper.writeValueAsString(header), request);

        return Optional.ofNullable(commonParameterList)
                .map(CommonResult::getData)
                .map(data -> objectMapper.convertValue(data, new TypeReference<List<BasAcctAcoDto>>() {}))
                .map(list -> list.stream().filter(dto -> "Y".equals(dto.getPrimaryHierId())).findFirst().get())
                .orElse(null);
    }

}
