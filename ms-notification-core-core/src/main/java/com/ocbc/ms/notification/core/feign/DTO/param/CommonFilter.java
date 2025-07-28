package com.ocbc.ms.notification.core.feign.DTO.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommonFilter {

    @Schema(description = "Field Name", example = "legalEntity")
    private String name;

    @Schema(description = "Field Value", example = "01")
    private Object value;

    @Schema(description = "Operator", example = "EQ")
    private String operator;
}
