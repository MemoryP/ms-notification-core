package com.ocbc.ms.notification.core.service.impl;

import cn.hutool.json.JSONUtil;
import com.ocbc.ms.cbs.core.context.HttpHeaderContext;
import com.ocbc.ms.cbs.core.notification.dto.NotificationProperties;
import com.ocbc.ms.notification.core.constant.SendMailConstant;
import com.ocbc.ms.notification.core.entity.kafkaemail.*;
import com.ocbc.ms.notification.core.enums.NotificationTypeEnum;
import com.ocbc.ms.notification.core.service.StaffNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class StaffNotificationServiceImpl implements StaffNotificationService {
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private NotificationProperties notificationProperties;

    @Override
    public String sendEmailByKafka(String template, HashMap<String, String> params, String email, String phone, String cc, String bcc,HashMap<String, Object> payload) {
        String topic = this.notificationProperties.getTopic();
        // build send info
        SendEmailInfo emailInfo = buildEmailDTO(topic, template, params, email, phone, cc, bcc,payload);
        String emailMsg = JSONUtil.toJsonStr(emailInfo);
        log.info("send kafka email,topic = {},emailMsg = {}",topic,emailMsg);
        if (this.notificationProperties.isEnabled()) {
            CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic, emailMsg);
            String message = String.format("template: %s to email:%s", template, email);
            future.thenAccept((result) -> {
                log.info("use kafka sent message= {}; topic= {}; partition:{} offset= {};", new Object[]{message, topic, result.getRecordMetadata().partition(), result.getRecordMetadata().offset()});
            });
            future.exceptionally((ex) -> {
                log.error("unable to send message= {}; topic= {};", new Object[]{message, topic, ex});
                return null;
            });
        }
        return JSONUtil.toJsonStr(emailInfo.getEventMessageVO().getPayload().getNotificationData().getRecipientInfo());
    }

    /**
     *
     * @param topic
     * @param template
     * @param contentParams
     * @param email
     * @param phone
     * @param cc
     * @param bcc
     * @return
     */
    private SendEmailInfo buildEmailDTO(String topic, String template, HashMap<String, String> contentParams, String email, String phone, String cc, String bcc, HashMap<String, Object> payload) {
        RecipientInfo recipientInfo = new RecipientInfo();
        recipientInfo.setEmail(email);
        recipientInfo.setPhone(phone);
        recipientInfo.setCc(cc);
        recipientInfo.setBcc(bcc);
        recipientInfo.setSubject(String.valueOf(payload.get(SendMailConstant.SUBJECT)));

        SendEmailNotificationInfo notificationInfo = new SendEmailNotificationInfo();
        notificationInfo.setRecipientInfo(recipientInfo);
        notificationInfo.setChannels(List.of(NotificationTypeEnum.EMAIL.name()));

        SendEmailPayloadInfo payloadInfo = new SendEmailPayloadInfo();
        payloadInfo.setTxtData(contentParams);
        payloadInfo.setNotificationData(notificationInfo);

        SendEmailMessageInfo emailMessageInfo = new SendEmailMessageInfo();
        emailMessageInfo.setPayload(payloadInfo);
        emailMessageInfo.setItemCode(template);

        SendEmailInfo emailInfo = new SendEmailInfo();
        emailInfo.setTopic(topic);
        emailInfo.setCorrelationId(HttpHeaderContext.getCorrelationId());
        emailInfo.setSourceId(HttpHeaderContext.getSourceId());
        emailInfo.setSourceCountry(HttpHeaderContext.getSourceCountry());
        emailInfo.setEventMessageVO(emailMessageInfo);
        return emailInfo;
    }
}
