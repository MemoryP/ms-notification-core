package com.ocbc.ms.notification.core.entity.rsp;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AlertTemplateRetrieveResponse {
    private String templateId;
    private String language;
    private String deliveryChannel;
    private String description;
    private String content;
    private String createdBy;
    private LocalDateTime createdTime;
    private String lastUpdatedBy;
    private LocalDateTime lastUpdatedTime;

    private List<AlertTemplateGlobalTagResponse> tags;

    @Getter
    @Setter
    public static class AlertTemplateGlobalTagResponse{
        private String tagId;
        private String desc;
    }
}
