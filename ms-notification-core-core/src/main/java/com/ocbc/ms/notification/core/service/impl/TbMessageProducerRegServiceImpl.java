package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.notification.core.entity.TbMessageProducerReg;
import com.ocbc.ms.notification.core.entity.dto.ProducerRegDto;
import com.ocbc.ms.notification.core.repository.TbMessageProducerRegRepository;
import com.ocbc.ms.notification.core.service.TbMessageProducerRegService;
import com.ocbc.ms.notification.core.utils.SnowFlakeIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TbMessageProducerRegServiceImpl implements TbMessageProducerRegService {

    private final SnowFlakeIdGenerator snowFlakeIdGenerator;

    private final TbMessageProducerRegRepository tbMessageProducerRegRepository;

    @Override
    public void saveTbMessageProducerReg(ProducerRegDto producerRegDto) {
        TbMessageProducerReg tbMessageProducerReg = new TbMessageProducerReg();
        tbMessageProducerReg.setId(snowFlakeIdGenerator.generate())
                .setConsumerId(producerRegDto.getConsumerId())
                .setLegalEntity(producerRegDto.getLegalEntity())
                .setEventTypeCode(producerRegDto.getEventTypeCode())
                .setNotificationTopic(producerRegDto.getNotificationTopic())
                .setNotificationType(producerRegDto.getNotificationType())
                .setPayload(producerRegDto.getPayload())
                .setCreatedTime(new Date())
                .setUpdatedTime(new Date());
        tbMessageProducerRegRepository.saveAndFlush(tbMessageProducerReg);
    }
}
