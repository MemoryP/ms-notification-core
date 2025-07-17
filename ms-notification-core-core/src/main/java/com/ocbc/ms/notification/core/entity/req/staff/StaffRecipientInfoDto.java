package com.ocbc.ms.notification.core.entity.req.staff;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class StaffRecipientInfoDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String email;

    private String phone;

    private String cc;

    private String bcc;
}
