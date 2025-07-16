package com.ocbc.ms.notification.core.entity.rsp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ContactProfile {

    private List<ContactInfo> contactInfoList;

}
