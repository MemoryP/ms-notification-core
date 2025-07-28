package com.ocbc.ms.notification.core.feign.DTO.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressInfo {
    /**
     * Sequence Number
     */
    private Long sequenceNo;
    /**
     * Type of Address
     */
    private CodeValueInfo addressType;
    /**
     * ADDR_LAG
     */
    private CodeValueInfo addressLanguage;
    /** ADDR_DEFAULT_FLAG
     * Default Address
     */
    private Boolean isDefault;
    /** ADDR_FORMAT_CODE
     * Address Format Code
     */
    private CodeValueInfo formatCode;
    /**
     * Address Line 1
     */
    @NotBlank
    @Size(max = 35, message = "Maximum length has been reached: Address line 1")
    private String addressLine1;
    /**
     * Address Line 2
     */
    @Size(max = 35, message = "Maximum length has been reached: Address line 2")
    private String addressLine2;
    /**
     * Address Line 3
     */
    @Size(max = 35, message = "Maximum length has been reached: Address line 3")
    private String addressLine3;
    /**
     * Address Line 4
     */
    @Size(max = 35, message = "Maximum length has been reached: Address line 4")
    private String addressLine4;
    /**
     * Address Country Code
     */
    private CodeValueInfo countryCode;
    /**
     * COUN_SUB_DIVISION
     */
    private CodeValueInfo provinceCode;

    @Size(max = 15, message = "Maximum length has been reached: Department")
    private String department;

    @Size(max = 15, message = "Maximum length has been reached: Sub Department")
    private String subDepartment;
    /**
     * Street Name
     */
    @Size(max = 30, message = "Maximum length has been reached: Street Name")
    private String streetName;
    /**
     * Building Number
     */
    @Size(max = 10, message = "Maximum length has been reached: Building Number")
    private String buildingNo;
    /**
     * BUILDING_NAME
     */
    @Size(max = 25, message = "Maximum length has been reached: Building Name")
    private String unitNo;
    /**
     * Storey/Level Number
     */
    @Size(max = 10, message = "Maximum length has been reached: Floor")
    private String storeyNo;

    @Size(max = 10, message = "Maximum length has been reached: Post Box")
    private String postalBox;

    @Size(max = 8, message = "Maximum length has been reached: Room")
    private String apartmentNo;
    /**
     * Postal Code
     */
    @Size(max = 6, message = "Maximum length has been reached: Post Code")
    private String postalCode;
    /**
     * CITY_NAME
     */
    private CodeValueInfo cityCode;

    @Size(max = 20, message = "Maximum length has been reached: Town Location Name")
    private String cityLocationName;
    /**
     * DISTRICT_NAME
     */
    private CodeValueInfo districtCode;
    private String remark;
    private String lostFlag;
    private String lostDate;
    private String rejectMailFlag;
    private String rejectMailUpdateDate;


    private Boolean isChequePrintName;
    private String lastUpdatedDateTime;

    private String operationType;

    private Integer acctAddrRelCounter;

    /**
     * Delivery status
     */
    private CodeValueInfo deliveryStatus;
}
