package com.ocbc.ms.notification.core.entity.rsp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomerRspDto {

    private ContactProfile contactProfile;

}
