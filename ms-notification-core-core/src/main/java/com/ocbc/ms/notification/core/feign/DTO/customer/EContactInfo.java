package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EContactInfo {
    /**
     * Sequence Number
     */
    private Long sequenceNo;
    /**
     * Contact Type (electronic)
     */
    private CodeValueInfo contactType;
    @Size(max = 150)
    private String contactName;
    private CodeValueInfo country;
    @Size(max = 10)
    private String areaCode;
    /**
     * TEL_NUM
     */
    @Size(max = 36)
    private String contactNo;
    /**
     * EXT_TEL_NUM
     */
    @Size(max = 32)
    private String extensionPhoneNo;
    /**
     * CONTACT_VALUE
     */
    @Size(max = 50)
    private String contactValue;

    /**
     * DEFAULT_CONTACT_FLAG
     */
    private Boolean isDefault;

    /**
     * REMARK
     */
    @Size(max = 50)
    private String remarks;

    private Boolean isOTP;

    private String lastUpdatedDateTime;

    /**
     * AUTHORIZE
     */
    @Size(max = 40)
    private String authorizerPosition;

    @JsonProperty("isEAlert")
    private Boolean isEAlert;

    @JsonProperty("isInvestEAlert")
    private Boolean isInvestEAlert;

    private String operationType;

    private String contactId;

    private String role;

    private CodeValueInfo state;

    /**
     * Delivery status
     */
    private CodeValueInfo deliveryStatus;
}
