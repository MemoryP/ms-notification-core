package com.ocbc.ms.notification.core.feign.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignRequestHeader {
    @JsonProperty("x-correlation-id")
    private String correlationId;

    @JsonProperty("x-source-id")
    private String sourceId;

    @JsonProperty("x-source-country")
    private String sourceCountry;

    @JsonProperty("x-source-date-time")
    private String sourceDateTime;

    @JsonProperty("x-legal-entity")
    private String legalEntity;
}
