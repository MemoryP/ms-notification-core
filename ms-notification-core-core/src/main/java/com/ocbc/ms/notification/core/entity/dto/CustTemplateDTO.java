package com.ocbc.ms.notification.core.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CustTemplateDTO {
    private String templateId;
    private String language;
    private String deliveryChannel;
    private List<String> applyTo;
    private LocalDateTime effectiveDate;
    private LocalDateTime expiryDate;
    private String description;
    private String content;
    private String createdBy;
    private LocalDateTime createdTime;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedTime;
}
