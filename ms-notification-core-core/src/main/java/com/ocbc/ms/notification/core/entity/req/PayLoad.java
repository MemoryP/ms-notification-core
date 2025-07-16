package com.ocbc.ms.notification.core.entity.req;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PayLoad implements Serializable {

    private static final long serialVersionUID = 10086L;

    private UserInfo user;

    private JsonNode notificationData;
}
