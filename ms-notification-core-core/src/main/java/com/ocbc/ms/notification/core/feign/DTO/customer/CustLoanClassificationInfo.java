package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * ============================================================
 *
 * @describe: CustLoanClassificationInfo
 * @author: A5141914
 * @date: 2024/09/12
 * ============================================================
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustLoanClassificationInfo {

    /**
     * CIF Level Loan Class (HKMA standard)
     */
    private CodeValueInfo hkmaLevel;

    /**
     * Date of Loan Class changed (HKMA)
     */
    @Size(max = 8)
    private String hkmaDate;

    /**
     * CIF Level Loan Class (MAS standard)
     */
    private CodeValueInfo masLevel;

    /**
     * Date of Loan Class changed (MAS)
     */
    @Size(max = 8)
    private String masDate;

    /**
     * CIF Level Loan Class (AMCM standard)
     */
    private CodeValueInfo amcmLevel;

    /**
     * Date of Loan Class changed (AMCM)
     */
    @Size(max = 8)
    private String amcmDate;

    /**
     * Bloomberg Equity Ticker
     */
    @Size(max = 15)
    private String bloombergEquityTicker;

    /**
     * 	Vehicle Plate Number
     */
    @Size(max = 15)
    private String vehiclePlateNumber;

    /**
     * Complex Structure Indicator
     */
    private Boolean isComplexStructure;

    /**
     * Carbon emission
     */
    @Size(max = 15)
    private String carbonEmission;

    /**
     * Energy consumption. unit is kWh
     */
    @Size(max = 15)
    private String energyKwhUnit;

    /**
     * Water consumption. unit is m3
     */
    @Size(max = 15)
    private String energyM3Unit;

    /**
     * Waste reduction
     */
    @Size(max = 15)
    private String wasteReduction;

    /**
     * Training hours. unit is in hour
     */
    @Size(max = 15)
    private String trainingHours;

    /** RCC_CODE_1
     * Risk Concentration Code 1
     */
    private CodeValueInfo rccCodeOne;

    /** RCC_CODE_2
     * Risk Concentration Code 2
     */
    private CodeValueInfo rccCodeTwo;

    /**
     * General Provision Currency
     */
    private CodeValueInfo generalProvisionCcy;

    /**
     * General Provision Amount
     */
    private BigDecimal generalProvisionAmount;

    /**
     * Specific Provision Currency
     */
    private CodeValueInfo specificProvisionCcy;

    /**
     * Specific Provision Amount
     */
    private BigDecimal specificProvisionAmount;
}
