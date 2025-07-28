package com.ocbc.ms.notification.core.feign.DTO.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * CustomerRelInfo
 *
 * @author Felix
 * @date 2023/10/17
 * @description:
 */
@Data
public class CustomerRelInfo implements Serializable {
    /**
     * Sequence Number
     */
    private Long sequenceNo;

    /**
     * Natural Person
     * Legal Entity
     */
    private CodeValueInfo customerCategory;
    /**
     * Customer Relationship Type
     */
    private CodeValueInfo relationshipType;
    /**
     * Related Customer Id
     */
    @Size(max = 12)
    private String customerId;

    @Size(max = 12)
    private String customerFormatId;

    /**
     * Related Customer Id
     */
    @Size(max = 12)
    private String relatedCustomerId;

    @Size(max = 12)
    private String relatedCustomerFormatId;

    /**
     * First Name In English
     */
    @Size(max = 250)
    private String firstName;
    /**
     * Customer Chinese First Name
     */
    private String localFirstName;
    /**
     * Last Name In English
     */
    @Size(max = 140)
    private String lastName;
    /**
     * Customer Chinese Last Name
     */
    private String localLastName;
    /**
     * Customer Name In English
     */
    @Size(max = 140)
    private String customerNameIntl;
    /**
     * Customer Name In Local Language
     */
    @Size(max = 140)
    private String customerNameLocal;
    /**
     * Share Percentage
     */
    @Size(max = 6)
    private String sharePercentage;
    /**
     * Share Percentage
     */
    @Size(max = 6)
    private String shareHoldPercentage;

    /**
     * Indicate IS IT Same Group
     */
    @JsonProperty(value = "isSameGroup")
    private Boolean isSameGroup;

    private CodeValueInfo roleInOcbc;
    /**
     * Informal Relation Flag
     * Y-Formal
     * N-Informal
     */
    private Boolean isInformalRelation;
    /**
     * Start Date
     */
    @Size(max = 8)
    private String startDate;
    /**
     * Close Date
     */
    @Size(max = 8)
    private String closeDate;
    /**
     * Close Date
     */
    @Size(max = 100)
    private String remark;
    /**
     * Verified
     */
    private String isIdDocNoVerified;
    /**
     * Accounts Exist
     */
    private Boolean isAccountsExist;
    /**
     * Branch No
     */
    @Size(max = 12)
    private String branchNo;

    @Size(max = 1)
    private String relationshipStatus;

    @Size(max = 2)
    private String customerStatus;

    private String extraDetail;

    private String lastUpdatedUser;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private String lastUpdatedDateTime;

    private String operationType;

    private Boolean canSuspend;

    private String staffEntity;
    /**
     * Source Type. G-GCIF, C-CIF
     */
    @Size(max = 1)
    private String sourceType;

    private String idIssueCountry;
    private String idType;
    private String idNo;
    private String secondIdType;
    private String secondIdNo;
    private String secondIdIssueCountry;
    private String industry;
    private String gender;
    private String brNo;
    private String brIssueCountry;
    private String ciNo;
    private String ciIssueCountry;
    private String ubiNo;
    private String ubiIssueCountry;
    private String birthDate;
    private String nationality;
    private Long registrationDate;
    private String registrationCountryCode;
}
