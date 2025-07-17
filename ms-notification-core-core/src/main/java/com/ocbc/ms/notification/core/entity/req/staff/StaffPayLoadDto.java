package com.ocbc.ms.notification.core.entity.req.staff;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class StaffPayLoadDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private JsonNode txtData;

    private StaffNotificationDataDto notificationData;
}
