package com.ocbc.ms.notification.core.feign.DTO.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(name = "PDPO")
public class PDPO implements Serializable {

    private Long sequenceNo;

    @Schema(example = "true", description = "customer accept mailing")
    private Boolean isMailing;

    @Schema(example = "true", description = "customer accept email")
    private Boolean isEmail;

    @Schema(example = "true", description = "customer accept tel")
    private Boolean isTel;

    @Schema(example = "true", description = "customer accept sms")
    private Boolean isSms;

    @Schema(example = "OHK")
    @Length(max = 10)
    private String organization;

    @Schema(example = "789")
    @Size(max = 12)
    private String requestBranchNo;

    private String operationType;
}
