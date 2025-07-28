package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * ============================================================
 * @describe:               DocumentInfo
 * @author:                 A5141914
 * @date:                   2024/10/10
 * ============================================================
 */
@Data
public class DocumentInfo {
    /**
     * Sequence Number
     */
    private Long sequenceNo;
    /** PRIMARY_ID_FLAG
     * Is the document is Primary document
     */
    @JsonProperty(value = "isMainDocument")
    private Boolean isMainDocument;
    /**
     * Document Issuance Organization Code
     */
    private String issueOrgCode;
    /** ID_ISSUED_COUN
     * Document Issuance Country
     */
    private CodeValueInfo issueCountry;
    /**
     * Country of Residence for Personal Customer only (option: Y/N)
     */
    @Size(max = 1)
    private String residenceCountryFlag;
    /**
     * Place of Incorporation for Corporate Customer only (can different for ID Issuing Country）
     */
    private CodeValueInfo placeOfIncorporation;

    /** ID_TYPE
     * Document Type
     */
    private CodeValueInfo documentType;
    /** ID_NO
     * Document Number
     */
    @Size(max = 25)
    private String documentNo;
    /** ID_EFFECT_DATE
     * Effective Date
     */
    @Size(max = 8)
    private String effectiveDate;
    /** ID_EXP_DATE
     * Expiry Date
     */
    @Size(max = 8)
    private String expiryDate;
    /**
     * remarks
     */
    private String remarks;
    /**
     * Last Maintenance Date & Time
     */
    private String lastUpdatedDateTime;

    private String operationType;
}
