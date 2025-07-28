package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.ocbc.ms.cbs.core.validation.constraint.LongDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BasicProfile {

    /* =================================== base info ===================================== */
    /**
     * cif Category
     */
    private CodeValueInfo cifCategory;

    /**
     * previous cif No list
     */
    private List<String> previousCifNoList;

    /**
     * customer Id, if entity is a customer of the bank
     */
    @Size(max = 12)
    private String customerId;

    @Size(max = 12)
    private String customerFormatId;

    /**
     * full name
     */
    @Size(max = 200)
    private String customerNameIntl;

    /**
     * local full name
     */
    @Size(max = 200)
    private String customerNameLocal;

    /**
     * print name1
     */
    @Size(max = 35)
    private String printName1;

    /**
     * print name2
     */
    @Size(max = 35)
    private String printName2;

    /**
     * print name1(local)
     */
    @Size(max = 35)
    private String localPrintName1;

    /**
     * print name2(local)
     */
    @Size(max = 35)
    private String localPrintName2;

    /**
     * short name
     */
    @Size(max = 20)
    private String shortName;

    /**
     * Customer category indicating high net worth, personal banking customer, wealth customer, etc.
     */
    private CodeValueInfo customerCategory;

    /**
     * customer level
     */
    private CodeValueInfo customerLevel;

    /**
     * Customer Type
     */
    private CodeValueInfo customerType;

    /**
     * Customer Sub Type
     */
    private CodeValueInfo customerSubType;

    /**
     * CIF type
     */
    private CodeValueInfo cifType;

    /**
     * segment code
     */
    private CodeValueInfo segmentCode;

    /**
     * branch code for customer
     */
    private CodeValueInfo customerCreationBranchNo;

    private CodeValueInfo serviceUnitCode;

    /**
     * onboarding channel code(non-input field)
     *
     * @ignore
     */
    private String channelCode;

    /**
     * NATIONAL_ECONOMY_INDUSTRY
     * National Economic Industry Classification (China specific)
     */
    private CodeValueInfo nationalEcoIndustry;

    /**
     * Risk Info
     */
    @Valid
    private RiskInfo riskInfo;

    /**
     * FAX_BIZ
     * Indicates if customer allowed to send instruction to the bank via fax. e.g. like send one payment FAX instruction to Payment team to trigger the payment
     */
    private Boolean isFaxIndemnity;

    /**
     * FAX_BIZ_EXP_DATE
     * Expire Date for Fax Indemnity
     *
     * @size 8
     */
    @LongDate
    private String expiryFaxIndemnityDate;

    /**
     * BANK_CUST_FLAG
     * Indicates if the customer is bank's customer
     */
    private Boolean isBankCustomer;

    /**
     * OCBC_CUST_FLAG
     * Indicates if customer is customer of OCBC or OWHB etc.
     */
    @Size(max = 1)
    private String customerBank;

    /**
     * Primary Country of Domicile
     * RESIDENCE_COUN
     * Customer Residential Country code
     */
    private CodeValueInfo countryOfResidence;

    /**
     * Secondary Country of Domicile
     * SED_RESIDENCE_COUN
     * Customer this field will contain Secondary Exposure Country Code
     */
    private CodeValueInfo secondaryCountryOfResidence;

    /**
     * Primary Country of Exposure
     * EXPOSURE_COUN
     */
    private CodeValueInfo primaryCountryOfExposure;

    /**
     * Secondary Country of Exposure
     * SED_EXPOSURE_COUN
     */
    private CodeValueInfo secondaryCountryOfExposure;

    /**
     * Cif status1
     * CUST_STS
     */
    private CodeValueInfo customerStatus;

    /**
     * Cif status2
     * CUST_STS2
     */
    private CodeValueInfo customerStatus2;
    /**
     * Forced to close
     */
    private Boolean isForcedClose;
    /**
     * notification date for account closure
     */
    @Size(max = 8)
    private String acctCloseNotifyDate;
    /**
     * MAIL_TYPE
     */
    private CodeValueInfo mailType;
    /**
     * HOLD_MAIL_CODE
     */
    private CodeValueInfo holdMailType;

    /**
     * SMS_ACCT
     * To indicate if allow to send SMS alert to customer
     */
    private Boolean isSendSmsAlert;

    /**
     * Primary Industry Code
     * PRIMARY_INDUSTRY_CODE
     */
    private CodeValueInfo primaryIndustryCode;

    /**
     * Secondary Industry Code
     * SECONDARY_INDUSTRY_CODE
     */
    private CodeValueInfo secondaryIndustryCode;

    /**
     * third Industry Code
     * <p>TERTIARY_INDUSTRY_CODE</p>
     */
    private CodeValueInfo thirdIndustryCode;

    @Size(max = 32)
    private String recommender;

    private String upck;

    /**
     * Branch Indicator
     */
    private CodeValueInfo branchIndicator;

    /**
     * Bank
     */
    @Size(max = 1)
    private String bankFlag;

    /**
     * Risk Weighting Code
     */
    private CodeValueInfo riskWeightingCode;

    /**
     * Oracle segment
     */
    private CodeValueInfo oracleSegmentCode;

    /**
     * Parent Indicator
     */
    @Size(max = 1)
    private String parentCustFlag;

    /**
     * Parent CIF
     */
    @Size(max = 12)
    private String parentCustId;

    /**
     * Institution
     */
    private CodeValueInfo institutionCode;

    /**
     * Employment Type
     */
    private CodeValueInfo employmentType;

    /* =================================== personal info ===================================== */
    /**
     * PR_FLAG
     * Customer PR Flag
     */
    private Boolean isCustomerPR;

    /**
     * PR_ISSUED_COUN_CODE
     * Customer PR Country of Issuance
     */
    private CodeValueInfo customerPRIssueCountry;

    /**
     * RESIDENT_FLAG
     * Customer Resident Flag
     */
    private Boolean isResident;

    /**
     * salutation
     */
    @Size(max = 50)
    private String salutation;

    /**
     * Info about date and place of birth
     */
    @Valid
    private BirthInfo birthInfo;

    @Size(max = 200)
    private String lastName;

    @Size(max = 200)
    private String middleName;

    @Size(max = 200)
    private String firstName;

    @Size(max = 100)
    private String localFirstName;

    @Size(max = 100)
    private String localLastName;

    /**
     * Gender
     * CUST_SEX
     */
    private CodeValueInfo gender;

    /**
     * CUST_NATION
     */
    @Size(max = 3)
    private String customerNation;

    /**
     * Onshore -mainlander (Customer Geographics / Nature)
     * Onshore – HK/ Onshore -mainlander/ Onshore - PIC/ Onshore - others/ offshore -mainlander/ offshore- others
     */
    private CodeValueInfo customerGeoNature;

    /**
     * Marital Status
     * MARRY_STS
     */
    private CodeValueInfo maritalStatus;

    /**
     * primary nationality - country code
     * NATIONALITY
     */
    private CodeValueInfo customerNationality;

    /**
     * secondary nationality - country code
     * SED_NATIONALITY
     */
    private CodeValueInfo secondNationality;

    /**
     * Education Level
     * CUST_EDUCATION
     */
    private CodeValueInfo educationLevel;

    /**
     * health status
     */
    private CodeValueInfo healthStatus;

    /**
     * FX_BUREAU
     * State Administration Foreign Exchange (SAFE) Bureau Branch Code (China Specific)
     */
    private CodeValueInfo fxBureauBranchCode;

    /**
     * OUT_CABINET_CLEAN_FLAG
     * Indicates if customer use signing pad in branch instead of direct teller interaction
     */
    private Boolean isUsingSignaturePad;

    /**
     * SZ_ST_CUST_FLAG
     * Special Cooperation Zone
     */
    private Boolean isSpecialZone;

    /**
     * TRD_AC_IDENTIFICATION
     * Account Identity verification Type
     */
    @Size(max = 3)
    private String accountIdentityVerificationType;

    /**
     * E_BANKING_NO
     * Electronic Banking Login Number
     */
    @Size(max = 32)
    private String ebankingLoginNo;

    /**
     * class code
     */
    private CodeValueInfo classCode;

    /**
     * Staff indicator
     * STAFF_CODE
     */
    private Boolean isStaff;

    /**
     * vip indicator
     */
    private Boolean isVIP;

    private Boolean isWMC;
    /**
     * Agent Information
     */
    private AgentInfo agentInfo;



    /* =================================== company info ===================================== */
    /**
     * BIZ_PLACE
     */
    @Size(max = 300)
    private String businessPlace;

    /**
     * PRIMARY_INDUSTRY_COUN_CODE
     * business Located Country
     */
    private CodeValueInfo businessLocatedCountryCode;

    /**
     * BIZ_STS
     * Business Operating Status
     */
    private CodeValueInfo businessStatus;

    /**
     * BIZ_SCOPE
     * Business Scope
     */
    @Size(max = 1000)
    private String businessScope;

    /**
     * Place of incorporation
     * REG_COUN_CODE
     * Business Registration Country Code
     */
    private CodeValueInfo registrationCountryCode;

    /**
     * REG_REGION_CODE
     * Business Registration Region Code
     */
    @Size(max = 3)
    private String registrationRegionCode;

    /**
     * REG_DATE
     * Registration Date
     */
    @LongDate
    @Size(max = 8)
    private String registrationDate;

    /**
     * LOAN_CARD_CODE
     */
    @Size(max = 32)
    private String loanCardCode;

    /**
     * HEADQUARTERS_COUN_CODE
     * Country where the company is headquartered
     */
    private CodeValueInfo headquarterCountryCode;

    /**
     * FTZ_CODE
     * Free Trade Zone Code
     */
    private CodeValueInfo freeTradeZoneCode;

    /**
     * ECONOMIC_TYPE
     * Business Economic Type
     */
    private CodeValueInfo businessEconomicType;

    /**
     * SPECIAL_ECONOMIC_TYPE
     * Business Special Economic Type
     */
    private CodeValueInfo businessSpecialEconomicType;

    /**
     * NATIONAL_ECONOMIC_DEPT
     * National Economic Sector
     */
    private CodeValueInfo nationalEcoSector;

    // For INTL use GROUP_INDUSTRY_CODE
    // For OCHK use LOCAL_INDUSTRY_CODE
    // Delete validation
    private CodeValueInfo bizNature;

    @Size(max = 50)
    private String serviceNature;

    /**
     * STAFF_NUM
     * Number of employees
     */
    private List<StaffCount> staffCounts;

    /**
     * REGULATED_FI
     * Is it a regulated financial institution
     */
    private CodeValueInfo regulatedFI;

    private Boolean isShellCompany;

    /**
     * BLOOMBERG_EQUITY_TICKER
     * Bloomberg Equity Symbol
     */
    @Size(max = 20)
    private String bloombergEquitySymbol;

    @Size(max = 20)
    private String swiftCode;

    /**
     * COMP_CTRL_TYPE
     * Company Holding Type
     */
    private CodeValueInfo companyHoldingType;

//    private List<CustCtrlRegInfo> custCtrlRegInfoList;
    /**
     * RATING_ORG
     * Rating organization/agencies
     */
    @Size(max = 150)
    private String ratingOrganization;

    /**
     * RATING_LVL
     * Rating Level
     */
    @Size(max = 10)
    private String ratingLevel;

    /**
     * GENERAL_GOVN_FLAG
     * General Government Flag
     */
    private Boolean isGeneralGovernment;

    /**
     * IPO_FLAG
     * Is it a listed company
     */
    private Boolean isIPO;

    /**
     * INVESTED_OVERSEAS_COMP
     * Whether it is an overseas company invested by a domestic enterprise
     */
    private Boolean isInvestedOverseasCompany;

    /**
     * POTENTIAL_CREDIT_CUST
     * Potential credit/trade customers flag
     */
    private Boolean isPotentialCreditCustomer;

    /**
     * BASIC_OPEN_BANK
     * Basic account opening Branch
     */
    @Size(max = 150)
    private String basicAccountOpenBranchNo;

    /**
     * BASIC_AC_NO
     * Basic Account No
     */
    @Size(max = 32)
    private String basicAccountNo;

    /**
     * GROUP_CODE
     * Customer Group List
     */
    private List<CustomerGroupInfo> customerGroupList;

    private Boolean isStateOwned;

    /**
     * STATE_OWNSHIP
     * % of Ownership
     */
    private BigDecimal stateOwnership;

    /**
     * state owned country
     */
    private CodeValueInfo stateOwnedCountry;

    @Size(max = 150)
    private String parentName;

    /**
     * REMARK
     * Remarks Field
     */
    @Size(max = 150)
    private String remarksField;

    /**
     * GREEN_INDUSTRY_FLAG
     * Indicates it is a green industry
     */
    private Boolean isGreenIndustry;

    /**
     * UNABLE_CONTACT_FLAG
     * Uncontactable Flag
     */
    private Boolean isUncontactable;

    /* =================================== other info ===================================== */
    /**
     * Customer Ethnic Group
     */
    private CodeValueInfo customerEthnicGroup;

    /**
     * List of Customer Alternative Names e.g. Alias, Previous Name etc.
     */
    @Valid
    private List<AlternativeName> customerAlternativeNameList;

    /**
     * List of Documents incl. certificates
     */
    @Valid
    private List<DocumentInfo> documentList;

    /**
     * list	List of related entities. List of related entities (e.g. spouse, child in Consumer Banking context. In Corporate Banking Context these could be corporate shareholders etc.)
     */
    private List<CustomerRelInfo> relatedEntities;

    private List<CustomerConnectedPartyInfo> connectedParties;

    /**
     * CRM info incl. Relationship Managers
     */
    @Valid
    private CrmInfo crmInfo;

    /**
     * Id for the customer's primary Relationship Manager
     */
    private CodeValueInfo rmId;

    private String buildTellerBranchNo;

    private String buildTellerNo;

    private String tellerNo;

    private String createDateTime;

    private String lastUpdatedDateTime;

    /**
     * Mark Deletion Logic - CIF Deletion Date
     */
    private String deletionDate;

    /**
     * Mark Deletion Logic - All Accounts Closed Date
     */
    private String allAccountClosedDate;

    /**
     * SWIFT Financial Institution
     */
    @Size(max = 140)
    private String swiftFI;

    /**
     * Prefix BIC code
     */
    @Size(max = 8)
    private String prefixBicCode;

    /**
     * Suffix SWIFT BIC Code
     */
    @Size(max = 3)
    private String suffixSwiftBicCode;

    /**
     * Suffix Local RTGS BIC Code
     */
    @Size(max = 3)
    private String suffixLocalRtgsBicCode;


    private String suspendedDate;

    private CodeValueInfo suspendedReason;

    private String suspendedRemarks;

    /**
     * Primary Industry Code (HKSIC)
     * PRIMARY_INDUSTRY_CODE_HKSIC
     */
    private CodeValueInfo primaryIndustryCodeHKSIC;

    /**
     * Secondary Industry Code (HKSIC)
     * SECONDARY_INDUSTRY_CODE_HKSIC
     */
    private CodeValueInfo secondaryIndustryCodeHKSIC;

    /**
     * Tertiary Industry Code (HKSIC)
     * TERTIARY_INDUSTRY_CODE_HKSIC
     */
    private CodeValueInfo thirdIndustryCodeHKSIC;

    /**
     * Business Type
     * BUSINESS_TYPE
     */
    private CodeValueInfo businessType;

}
