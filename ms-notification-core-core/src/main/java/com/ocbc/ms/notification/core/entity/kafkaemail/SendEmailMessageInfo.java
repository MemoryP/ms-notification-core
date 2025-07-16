package com.ocbc.ms.notification.core.entity.kafkaemail;

import lombok.Data;

@Data
public class SendEmailMessageInfo {
    private String itemCode;
    private SendEmailPayloadInfo payload;
}
