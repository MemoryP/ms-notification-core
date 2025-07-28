package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.ocbc.ms.cbs.core.validation.constraint.LongDate;
import lombok.Data;

@Data
public class RelationshipManager {
    /**
     * sequence number
     */
    private Long sequenceNo;
    /**
     * Account Manager Relationship Types
     */
    private CodeValueInfo rmType;
    /**
     * Id for the customer's Relationship Manager
     */
    private CodeValueInfo rmId;
    /**
     * Relationship Manager Name
     */
    private String rmNameLocal;

    /**
     * Segment Code
     */
    private String segmentCode;
    /**
     * effective date
     */
    @LongDate
    private String effectiveDate;
    /**
     * end date
     */
    @LongDate
    private String endDate;
    /**
     * branch code
     */
    private String branchNo;
    /**
     * Manager - Loan control Unit
     */
    private String managerLoanControlUnit;
    /**
     * remarks
     */
    private String remarks;
    /**
     * last updated
     */
    private String lastUpdatedDateTime;

    private String operationType;
}
