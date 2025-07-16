package com.ocbc.ms.notification.core.entity.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KafkaProducerDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String topic;

    private EventMessageVO eventMessageVO;

    public static KafkaProducerDto init(){
        KafkaProducerDto kafkaProducerDto = new KafkaProducerDto();
        PayLoad payLoad = new PayLoad();
        UserInfo userInfo = new UserInfo();
        payLoad.setUser(userInfo);
        EventMessageVO eventMessageVO1 = new EventMessageVO();
        eventMessageVO1.setPayLoad(payLoad);
        kafkaProducerDto.setEventMessageVO(eventMessageVO1);
        return kafkaProducerDto;
    }
}
