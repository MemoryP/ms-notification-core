package com.ocbc.ms.notification.core.entity.kafkaemail;

import lombok.Data;

@Data
public class RecipientInfo {
    private String email;
    private String phone;
    private String subject;
    private String cc;
    private String bcc;
}
