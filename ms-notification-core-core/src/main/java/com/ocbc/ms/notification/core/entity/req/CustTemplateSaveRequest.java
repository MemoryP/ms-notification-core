package com.ocbc.ms.notification.core.entity.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CustTemplateSaveRequest {

    @NotBlank
    private String templateId;

    @NotBlank
    private String language;

    @NotBlank
    private String deliveryChannel;

    @NotBlank
    private String description;

    @NotBlank
    private String templateContent;

    private List<String> tags;

    private List<String> applyTo;

    private LocalDateTime effectiveDate;

    private LocalDateTime expiryDate;

}
