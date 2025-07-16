package com.ocbc.ms.notification.core.entity.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertTemplateInqueryRequest {

    private String templateId;

    private String language;

    private String deliveryChannel;

    @Min(value = 1, message = "size value must be greater than 0")
    @Max(value = 100, message = "size value must be less than 100")
    private int pageSize;

    @Min(value = 0, message = "page value must be greater than 0")
    private int pageNo;
}
