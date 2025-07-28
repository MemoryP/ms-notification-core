package com.ocbc.ms.notification.core.feign.DTO.customer;

import lombok.Data;

@Data
public class CustomerGroupInfo {
    /**
     * sequence number
     */
    private Long sequenceNo;
    /**
     * Group code
     */
    private String groupCode;
    /**
     * Group Chinese name
     */
    private String groupNameChinese;
    /**
     * ISO Format
     */
    private String lastUpdatedDateTime;
}
