package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RiskInfo {
    /** FINANCIAL_RISK_TYPE
     * Financial Risk Type (risk appetite of customer)
     */
    private CodeValueInfo riskType;
    /** RISK_LVL
     * Risk Assessment Level (Result of Customer Due Diligent process)
     */
    private CodeValueInfo riskLevel;
    /** RISK_TYPE_ASSESS_DATE
     * date of Financial Risk Type assessment (risk appetite of customer)
     */
    @Size(max = 8)
    private String riskTypeAssessmentDate;
    /** NEXT_REVIEW_DATE
     * date by which customer's risk appetite must be assessed (riskType)
     */
    @Size(max = 8)
    private String riskTypeNextReviewDate;

    private String riskScoringDistribution;

    @Size(max = 8)
    private String riskTypeLastReviewDate;


    /**
     * It is currently empty and will be used in the future
     */
    private CodeValueInfo rccCodeOne;

    /**
     * It is currently empty and will be used in the future
     */
    private CodeValueInfo rccCodeTwo;

    private Boolean isHighValue;
}
