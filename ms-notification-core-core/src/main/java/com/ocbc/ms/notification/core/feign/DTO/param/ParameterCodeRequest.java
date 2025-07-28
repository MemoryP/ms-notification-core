package com.ocbc.ms.notification.core.feign.DTO.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ParameterCodeRequest {

    @Schema(example = "BasCurrency")
    private String code;

    @Schema(description = "filter")
    private List<CommonFilter> filter;

    @Schema(description = "order")
    private List<CommonOrder> order;

}
