package com.ocbc.ms.notification.core.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertTemplateDTO {
    private String templateId;
    private String language;
    private String deliveryChannel;
    private String description;
    private String content;
    private String createdBy;
    private LocalDateTime createdTime;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedTime;
}
