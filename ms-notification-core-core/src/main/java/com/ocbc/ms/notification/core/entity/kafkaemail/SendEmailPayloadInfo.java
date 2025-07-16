package com.ocbc.ms.notification.core.entity.kafkaemail;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SendEmailPayloadInfo {
    private Map<String,String> txtData;
    private SendEmailNotificationInfo notificationData;
    private List<SendEmailAttachmentDetailInfo> attachments;
}
