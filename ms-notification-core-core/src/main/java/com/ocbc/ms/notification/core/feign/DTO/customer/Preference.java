package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Preference {

    private CodeValueInfo comLang;

    private CodeValueInfo spokenLang;
    @JsonProperty("ATMLang")
    private CodeValueInfo ATMLang;
    private CodeValueInfo comChannel;
    private CodeValueInfo phoneCallTiming;
    private CodeValueInfo investEAlertLang;
    private CodeValueInfo statementMedia;
    @Size(max = 1)
    private String consentInvestPurposeFlag;
    @Size(max = 1)
    private String emailIndemnityFlag;
    @Size(max = 8)
    private String emailIndemnityDate;
    @Size(max = 1)
    private String faxIndemnityFlag;
    @Size(max = 8)
    private String faxIndemnityDate;
    @Size(max = 1)
    private String callbackIndemnityFlag;
    @Size(max = 8)
    private String callbackIndemnityDate;
    /**
     * Call Back Waiver
     */
    @Size(max = 1)
    private String callbackWaiverFlag;
}
