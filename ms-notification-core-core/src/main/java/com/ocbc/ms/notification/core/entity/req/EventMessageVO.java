package com.ocbc.ms.notification.core.entity.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EventMessageVO implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String type;

    private PayLoad payLoad;

}
