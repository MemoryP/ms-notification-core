package com.ocbc.ms.notification.core.entity.kafkaemail;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class SendEmailInfo {
    private String topic;
    private String correlationId;
    private String sourceId;
    private String sourceCountry;
    private SendEmailMessageInfo eventMessageVO;
    private String messageBody;

    public String getEmail() {
        try{
            return this.getEventMessageVO().getPayload().getNotificationData().getRecipientInfo().getEmail();
        }catch (Exception e){
            log.error("send email by kafka error in SendEmailInfo.getEmail() error is:{}", e.getMessage());
            return null;
        }
    }
    public String getCC() {
        try{
            return this.getEventMessageVO().getPayload().getNotificationData().getRecipientInfo().getCc();
        }catch (Exception e){
            log.error("send email by kafka error in SendEmailInfo.getCC() error is:{}", e.getMessage());
            return null;
        }
    }
    public String getBCC() {
        try{
            return this.getEventMessageVO().getPayload().getNotificationData().getRecipientInfo().getBcc();
        }catch (Exception e){
            log.error("send email by kafka error in SendEmailInfo.getBCC() error is:{}", e.getMessage());
            return null;
        }
    }
    public String getPhone() {
        try{
            return this.getEventMessageVO().getPayload().getNotificationData().getRecipientInfo().getPhone();
        }catch (Exception e){
            log.error("send email by kafka error in SendEmailInfo.getPhone() error is:{}", e.getMessage());
            return null;
        }
    }

    public Map<String, String> getFillDataMap() {
        Map<String, String> map = new HashMap<>();
        if (Objects.isNull(this.getEventMessageVO()) || Objects.isNull(this.getEventMessageVO().getPayload().getTxtData())) {
            return null;
        }
        try {
            map = this.getEventMessageVO().getPayload().getTxtData();
        } catch (Exception e) {
            log.error("send email by kafka error in SendEmailInfo.getFillDataMap() error is:{}", e.getMessage());
            return map;
        }

        return map;
    }

    public List<SendEmailAttachmentDetailInfo> getAttachmentList() {
        try {
            return this.getEventMessageVO().getPayload().getAttachments();
        }catch (Exception e){
            log.error("send email by kafka error in SendEmailInfo.getAttachmentList() error is:{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<String> getChan() {
        try {
            return this.getEventMessageVO().getPayload().getNotificationData().getChannels();
        }catch (Exception e){
            log.error("send email by kafka error in SendEmailInfo.getChan() error is:{}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public String getItemCode() {
        if (Objects.isNull(this.getEventMessageVO())) {
            return null;
        }
        return this.getEventMessageVO().getItemCode();
    }
}
