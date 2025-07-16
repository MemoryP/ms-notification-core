package com.ocbc.ms.notification.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.ms.cbs.core.context.LegalEntityContext;
import com.ocbc.ms.notification.core.entity.TbMessageConditionDef;
import com.ocbc.ms.notification.core.entity.dto.ConditionDto;
import com.ocbc.ms.notification.core.entity.dto.ConsumerDto;
import com.ocbc.ms.notification.core.entity.dto.PkgDto;
import com.ocbc.ms.notification.core.entity.dto.ProducerRegDto;
import com.ocbc.ms.notification.core.producer.LocalKafkaProducer;
import com.ocbc.ms.notification.core.service.MessagePkgService;
import com.ocbc.ms.notification.core.service.TbMessageConditionDefService;
import com.ocbc.ms.notification.core.service.TbMessageConsumerRegService;
import com.ocbc.ms.notification.core.service.TbMessageProducerRegService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final TbMessageConditionDefService tbMessageConditionDefServiceImpl;

    private final TbMessageConsumerRegService tbMessageConsumerRegServiceImpl;

    private final TbMessageProducerRegService tbMessageProducerRegServiceImpl;

    private final MessagePkgService messagePkgServiceImpl;

    private final LocalKafkaProducer localKafkaProducer;

    private final ObjectMapper objectMapper;

    @Async("taskExecutor")
    public void messageHand(String message){
        //持久化 收到的消息
        try{
            ConsumerDto consumerDto = objectMapper.readValue(message, ConsumerDto.class);
            LegalEntityContext.setLegalEntity(consumerDto.getLegalEntity());
            String consumerId = tbMessageConsumerRegServiceImpl.saveTbMessageConsumerReg(consumerDto);
            //匹配条件
            ConditionDto conditionDto = new ConditionDto();
            conditionDto.setLegalEntity(consumerDto.getLegalEntity())
                    .setServiceCode(consumerDto.getServiceCode())
                    .setServiceSubCode(consumerDto.getServiceSubCode())
                    .setPayload(objectMapper.writeValueAsString(consumerDto));
            List<TbMessageConditionDef> tbMessageConditionDefList = tbMessageConditionDefServiceImpl.getTbMessageConditionDef(conditionDto);
            for(TbMessageConditionDef tbMessageConditionDef: tbMessageConditionDefList){
                //组装发送消息体
                PkgDto pkgDto = new PkgDto();
                pkgDto.setMessage(objectMapper.writeValueAsString(consumerDto));
                pkgDto.setTbMessageConditionDef(tbMessageConditionDef);
                String producerMessage = messagePkgServiceImpl.init(pkgDto);

                localKafkaProducer.sendMessage(tbMessageConditionDef.getNotificationTopic(), producerMessage);

                //发送消息持久化
                ProducerRegDto producerRegDto = new ProducerRegDto();
                producerRegDto.setConsumerId(consumerId)
                        .setLegalEntity(consumerDto.getLegalEntity())
                        .setNotificationTopic(tbMessageConditionDef.getNotificationTopic())
                        .setNotificationType(tbMessageConditionDef.getNotificationType())
                        .setEventTypeCode(tbMessageConditionDef.getEventTypeCode())
                        .setPayload(producerMessage);
                tbMessageProducerRegServiceImpl.saveTbMessageProducerReg(producerRegDto);
            }
        }catch(Exception e){
            log.info(e.toString());
        }

    }

}
