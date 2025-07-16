package com.ocbc.ms.notification.core.entity.rsp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StaffRspDto {

    private long id;

    private String type;

    private String externalId;

    private String externalName;

    private String profile;

    private long invalidLoginCount;

    private String status;

    private String jobTitle;

    private String email;

    private String workPhone;

}
