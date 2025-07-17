package com.ocbc.ms.notification.core.entity.req.staff;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class StaffNotificationDataDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private StaffRecipientInfoDto recipientInfo;

    private List<String> channels;
}
