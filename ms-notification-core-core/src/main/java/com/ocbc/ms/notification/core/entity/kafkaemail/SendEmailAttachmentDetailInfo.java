package com.ocbc.ms.notification.core.entity.kafkaemail;

import lombok.Data;

@Data
public class SendEmailAttachmentDetailInfo {
    private String fileName;
    private Object file;
}
