package com.ocbc.ms.notification.core.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustTemplateInqueryDTO {
    private String templateId;

    private String language;

    private String deliveryChannel;

    private List<String> applyTo;

    private int pageSize;

    private int pageNo;
}
