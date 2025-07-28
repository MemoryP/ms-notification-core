package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class CustDetailQueryRequest {

    /**
     * customer id
     * @required true
     */
    @Size(max = 12)
    @Pattern(regexp = "\\d*", message = "Customer ID must be a numeric string")
    private String customerId;

    private String documentNo;

    private String documentType;

    private String issueCountry;
}
