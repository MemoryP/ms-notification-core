package com.ocbc.ms.notification.core.feign.DTO.customer;

import lombok.Data;

import java.math.BigDecimal;

/**
 * CustomerConnectedPartyInfo
 *
 * @author Chen Wei Yan
 * @date 2024/8/23
 * @description:
 */
@Data
public class CustomerConnectedPartyInfo {

    private String connectedPartyCode;

    private String connectedPartyName;

    private String customerId;

    private String customerFormatId;

    private Long sequenceNo;

    private String customerCategory;

    private String firstName;

    private String lastName;

    private String customerNameIntl;

    private String customerNameLocal;

    private String relationshipType;

    private String remark;

    private String relationshipStatus;

    private Long startDate;

    private Long closeDate;

    private String roleInOcbc;

    private String groupName;

    private BigDecimal shareHoldPercentage;

    private String relatedCustomerId;

    private String relatedCustomerFormatId;

    private String lastUpdatedUser;

    private String lastUpdatedDateTime;
}
