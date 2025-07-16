package com.ocbc.ms.notification.core.entity.rsp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ContactInfo {

    private String sequenceNo;

    private CodeValueStructure contactType;

    private String contactNo;

    private String extensionPhoneNo;

    private String contactValue;

}
