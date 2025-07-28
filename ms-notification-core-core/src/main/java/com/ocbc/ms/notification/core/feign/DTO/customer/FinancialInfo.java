package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FinancialInfo {
    /**
     * INDI_MONTH_INCOME
     * Individual/Personal Monthly Income
     */
    private CodeValueInfo individualMonthlyIncome;

    /**
     * FAMILY_MONTH_INCOME
     * Total Household Monthly Income
     */
    private CodeValueInfo householdMonthlyIncome;

    /**
     * INDI_ANNUAL_INCOME
     * Individual/Personal Annual Income
     */
    private CodeValueInfo individualAnnualIncome;

    /**
     * FAMILY_FINANCIAL_ASSETS
     * Total Household Financial Assets
     */
    private CodeValueInfo householdFinancialAssets;

    /**
     * FAMILY_FINANCIAL_NET_ASSETS
     * Total Households Financial Net Worth
     */
    private CodeValueInfo householdFinancialNetWorth;

    /**
     * LIFESTYLE_NOTE
     * Lifestyle Note
     */
    @Size(max = 150)
    private String lifestyleNote;

    /**
     * ASSET_AMT
     * Total Asset Amount
     */
    private BigDecimal assetAmount;

    /**
     * ASSET_CUR
     * Total Asset Currency Code
     */
    private CodeValueInfo assetCurrencyCode;

    /**
     * OPG_INCOME_AMT
     * Operating Income Amount
     */
    private BigDecimal operatingIncomeAmount;

    /**
     * OPG_CUR_CODE
     * Operating Income Currency Code
     */
    private CodeValueInfo operatingIncomeCurrencyCode;

    /**
     * RECEIVE_AMT
     * Paid-in capital amount
     */
    private BigDecimal paidCapitalAmount;

    /**
     * RECEIVE_CUR
     * Paid-in capital currency
     */
    private CodeValueInfo paidCapitalCurrency;

    /**
     * NET_ASSETS_AMT
     * Net Assets Amount
     */
    private BigDecimal netAssetAmount;

    /**
     * NET_ASSETS_CUR
     * Net Assets Currency
     */
    private CodeValueInfo netAssetCurrency;

    /**
     * REG_AMT
     * Registered capital amount - amount
     */
    private BigDecimal registrationCapitalAmount;

    /**
     * REG_CUR
     * Registered capital amount - currency
     */
    private CodeValueInfo registrationCapitalCurrency;

    /**
     * purpose of account opening
     */
    // @DicDataCheck(groupCode = DicGroupCodeEnum.PURPOSE_OF_ACCOUNT_OPENING)
    private List<String> purposeOfAo;

    // @DicDataCheck(groupCode = DicGroupCodeEnum.SOURCE_OF_FUND)
    private List<String> sourceOfFund;

    // @DicDataCheck(groupCode = DicGroupCodeEnum.SOURCE_OF_WEALTH)
    private List<String> sourceOfWealth;

    private CodeValueInfo noOfTrx;

    private Boolean isFaceToFace;

    private CodeValueInfo amountInBank;

    private CodeValueInfo amountCcy;

    private BigDecimal monthIncome;

    private CodeValueInfo monthIncomeCcy;

    private CodeValueInfo natureOfActivity;

    private BigDecimal allowanceAmount;

    private CodeValueInfo allowanceAmountCcy;

    private BigDecimal grossMonthIncome;

    private CodeValueInfo grossMonthIncomeCcy;

    @Size(max = 6)
    private String financialYear;

    /**
     * Consolidated Financial Statement
     */
    private Boolean isConsFinSta;

    private Boolean isAudited;

    private Boolean isCustomerWaive;

    private CodeValueInfo waiveReason;


    private CodeValueInfo referralCode;

    private CodeValueInfo visaPermits;

    /**
     * Issuing (Visa/Entry Permit)
     */
    @Size(max = 8)
    private String visaPermitsIssuedDate;

    /**
     * Expiry Date(Vida/Entry Permit)
     */
    @Size(max = 8)
    private String visaPermitsIssuedExpiryDate;

    /**
     * CPI (corporate professional investor)
     */
    private Boolean isCorpProInvestor;

    /**
     * witness officer
     */
    private String witnessOfficer;

    /**
     * Identification Document is Verified
     */
    private String idDocNoVerifiedFlag;

    /**
     * COI verified
     */
    private Boolean isCOIVerified;

    private CodeValueInfo trxAmount;

    /**
     * reason of account opening
     */
    private List<String> reasonsOfAo;

    /**
     * Any Active Banking Product Holding
     */
    private Boolean hasActiveBankingProductHolding;

    /**
     * BOT - Identification Type
     */
    private CodeValueInfo botIdentificationType;

    /**
     * FI Code
     */
    @Size(max = 3)
    private String fiCode;

    /**
     * Branch FI Code
     */
    @Size(max = 4)
    private String branchFiCode;

    /**
     * Counterparty Type Code
     */
    private CodeValueInfo counterPartyType;

    /**
     * BOT - Primary Business Type
     */
    private CodeValueInfo botPrimaryBizType;

    /**
     * SLL Business Group 1
     */
    @Size(max = 10)
    private String sllBusinessGroup1;

    /**
     * SLL Business Group 2
     */
    @Size(max = 10)
    private String sllBusinessGroup2;

    /**
     * Business Size
     */
    private CodeValueInfo businessSize;

    /**
     * Revenue in THB
     */
    @Size(max = 20)
    private String revenueInTHB;

    /**
     * No. of Labor
     */
    @Size(max = 10)
    private String noOfLabor;

    /**
     * DPS Excluded Entities
     */
    private CodeValueInfo dpsExcludedEntities;
}
