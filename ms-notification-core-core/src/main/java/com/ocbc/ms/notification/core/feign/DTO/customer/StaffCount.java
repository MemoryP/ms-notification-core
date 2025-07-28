package com.ocbc.ms.notification.core.feign.DTO.customer;

import lombok.Data;

/**
 * ============================================================
 *
 * @describe: StaffCount
 * @author: A5141914
 * @date: 2024/10/02
 * ============================================================
 */
@Data
public class StaffCount {
    private Long sequenceNo;
    private CodeValueInfo regionCode;
    private CodeValueInfo employeeCount;

}
