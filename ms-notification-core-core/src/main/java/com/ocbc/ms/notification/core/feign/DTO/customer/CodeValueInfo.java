package com.ocbc.ms.notification.core.feign.DTO.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CodeValueInfo implements Serializable {
    /**
     * New Code Value
     */
    private String codeValue;
    /**
     * Old Code Value
     */
    private String codeOldValue;
    /**
     * Code Description
     */
    private String description;
}
