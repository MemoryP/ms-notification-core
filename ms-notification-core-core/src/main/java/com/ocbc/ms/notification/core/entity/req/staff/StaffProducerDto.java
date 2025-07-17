package com.ocbc.ms.notification.core.entity.req.staff;

import com.ocbc.ms.notification.core.entity.req.EventMessageVO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
public class StaffProducerDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String topic;

    private EventMessageVO eventMessageVO;

}
