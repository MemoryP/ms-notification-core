package com.ocbc.ms.notification.core.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertTemplateInqueryDTO {
    private String templateId;

    private String language;

    private String deliveryChannel;

    private int pageSize;

    private int pageNo;
}
