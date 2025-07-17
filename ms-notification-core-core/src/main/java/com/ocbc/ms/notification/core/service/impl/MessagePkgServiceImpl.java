package com.ocbc.ms.notification.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.ms.cbs.core.context.LegalEntityContext;
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
import com.ocbc.ms.notification.core.feign.param.client.MsCustomerClient;
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

                // cif info
                getCustomerInfo(consumerDto);

                StaffProducerDto staffProducerMessage = buildStaffMessage(consumerDto, serviceCodeTemplate.get());

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

    public StaffProducerDto buildStaffMessage(ConsumerDto consumerDto, ServiceCodeTemplateEntity template) {
        return StaffProducerDto.builder()
                .topic(staffTopic)
                .eventMessageVO(EventMessageVO.builder()
                        .itemCode(template.getAlterTemplateCode())
                        .payLoad(StaffPayLoadDto.builder()
                                .txtData(objectMapper.valueToTree(consumerDto))
                                .notificationData(StaffNotificationDataDto.builder()
                                        .channels(Arrays.asList(NotificationTypeEnum.EMAIL.name()))
                                        .recipientInfo(StaffRecipientInfoDto.builder()
                                                .email("")
                                                .phone("")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
    }

    private void getCustomerInfo(ConsumerDto consumerDto) throws JsonProcessingException {
        CustomerMessageDto customerMessageDto = objectMapper.readValue(consumerDto.getMessage(), CustomerMessageDto.class);
        //获取cif信息
        FeignRequestHeader header = new FeignRequestHeader();
        header.setCorrelationId(consumerDto.getServiceCode());
        header.setSourceId(consumerDto.getXSourceId());
        header.setSourceCountry(consumerDto.getXSourceCountry());
        header.setSourceDateTime(String.valueOf(new Date()));
        header.setLegalEntity(consumerDto.getLegalEntity());
        String encodedHeader = objectMapper.writeValueAsString(header);
        msCustomerClient.getCustomerInfo(encodedHeader, customerMessageDto.getCustomerId());
    }

}
