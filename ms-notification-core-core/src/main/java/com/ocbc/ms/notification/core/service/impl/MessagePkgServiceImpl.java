package com.ocbc.ms.notification.core.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ocbc.ms.notification.core.entity.TbMessageConditionDef;
import com.ocbc.ms.notification.core.entity.dto.PkgDto;
import com.ocbc.ms.notification.core.entity.req.KafkaProducerDto;
import com.ocbc.ms.notification.core.service.MessagePkgService;
import com.ocbc.ms.notification.core.spi.CifServiceSpi;
import com.ocbc.ms.notification.core.spi.StaffServiceSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
@Slf4j
public class MessagePkgServiceImpl implements MessagePkgService {

    @Autowired
    CifServiceSpi cifServiceSpi;

    @Autowired
    StaffServiceSpi staffServiceSpi;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public String init(PkgDto dto) {
        try{
            String message = dto.getMessage();
            TbMessageConditionDef tbMessageConditionDef = dto.getTbMessageConditionDef();
            //message给payload赋值 循环payload
//            JSONObject messageObject = JSONObject.parseObject(message);
//            JSONObject payloadObject = JSONObject.parseObject(tbMessageConditionDef.getPayload());
//            payloadObject.replaceAll((k, v) -> messageObject.getString(k));

            JsonNode messageObject = objectMapper.readTree(message);
            JsonNode payloadObject = objectMapper.readTree(tbMessageConditionDef.getPayload());

            Iterator<Map.Entry<String, JsonNode>> fieldsB = messageObject.fields();
            while (fieldsB.hasNext()) {
                Map.Entry<String, JsonNode> fieldB = fieldsB.next();
                String key = fieldB.getKey();

                if (payloadObject.has(key)) {
                    ((ObjectNode) payloadObject).set(key, fieldB.getValue());
                }
            }
            log.info(objectMapper.writeValueAsString(payloadObject));

            //组装给kafka发送的消息体
            KafkaProducerDto kafkaProducerDto = KafkaProducerDto.init();
            kafkaProducerDto.setTopic(tbMessageConditionDef.getNotificationTopic());
            kafkaProducerDto.getEventMessageVO().setType(tbMessageConditionDef.getEventTypeCode());
            kafkaProducerDto.getEventMessageVO().getPayLoad().setNotificationData(payloadObject);

            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setUserId("1234");
            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setUserName("jack");
            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setPhone("12345678901");
            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setEmail("jack@ocbc.com");

//        if(TopicRunner.getTopicName(Constants.TOPIC_CUSTOMER).equals(tbMessageConditionDef.getNotificationTopic())){
//            CustomerReqDto customerReqDto = new CustomerReqDto();
//            customerReqDto.setCustomerId(messageObject.getString("userId"));
//            CustomerRspDto customerRspDto = cifServiceSpi.queryCustomerInfo(customerReqDto);
//
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setUserId(messageObject.getString("userId"));
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setUserName(customerRspDto.getContactProfile().getContactInfoList().get(0).getExtensionPhoneNo());
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setPhone(customerRspDto.getContactProfile().getContactInfoList().get(0).getExtensionPhoneNo());
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setEmail(customerRspDto.getContactProfile().getContactInfoList().get(0).getContactNo());
//        }else{
//            List<String> list = new ArrayList<>();
//            list.add(messageObject.getString("userId"));
//            StaffReqDto staffReqDto = new StaffReqDto();
//            staffReqDto.setLanIdList(list);
//            List<StaffRspDto> staffRspDtos = staffServiceSpi.queryStaffInfo(staffReqDto);
//
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setUserId(staffRspDtos.get(0).getExternalId());
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setUserName(staffRspDtos.get(0).getExternalName());
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setPhone(staffRspDtos.get(0).getWorkPhone());
//            kafkaProducerDto.getEventMessageVO().getPayLoad().getUser().setEmail(staffRspDtos.get(0).getEmail());
//
//        }

            return objectMapper.writeValueAsString(kafkaProducerDto);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

}
