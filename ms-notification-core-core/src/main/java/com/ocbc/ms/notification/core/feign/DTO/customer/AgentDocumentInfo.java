package com.ocbc.ms.notification.core.feign.DTO.customer;

import lombok.Data;

@Data
public class AgentDocumentInfo {
    /** AGENT_ID_TYPE
     * Document type
     */
    private CodeValueInfo documentType;
    /**
     * Document Number
     */
    private String documentNo;
    /**
     * Effective Date
     */
    private String effectiveDate;
    /**
     * Expiry Date
     */
    private String expiryDate;
    /**
     * Last updated date and time
     */
    private String lastUpdatedDateTime;
}
