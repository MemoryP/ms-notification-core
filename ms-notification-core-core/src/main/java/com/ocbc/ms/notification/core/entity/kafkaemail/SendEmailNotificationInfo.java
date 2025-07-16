package com.ocbc.ms.notification.core.entity.kafkaemail;

import lombok.Data;

import java.util.List;

@Data
public class SendEmailNotificationInfo {
    private RecipientInfo recipientInfo;
    private List<String> channels;
}
