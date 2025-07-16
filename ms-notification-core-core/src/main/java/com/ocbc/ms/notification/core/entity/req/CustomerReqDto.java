package com.ocbc.ms.notification.core.entity.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomerReqDto {

    private String customerId;

    private String documentNo;

    private String issueCountry;

    private String documentType;

}
