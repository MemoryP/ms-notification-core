package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.notification.core.entity.dto.ProducerRegDto;
import com.ocbc.ms.notification.core.repository.TbMessageProducerRegRepository;
import com.ocbc.ms.notification.core.utils.SnowFlakeIdGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TbMessageProducerRegServiceImplTest {

    @Mock
    private SnowFlakeIdGenerator snowFlakeIdGenerator;

    @Mock
    private TbMessageProducerRegRepository tbMessageProducerRegRepository;

    @InjectMocks
    private TbMessageProducerRegServiceImpl tbMessageProducerRegServiceImpl;

    private final String testId = "test-id-123";
    private final String testConsumerId = "consumer-1";
    private final String testLegalEntity = "TEST_LEGAL_ENTITY";
    private final String testTopic = "test.topic";
    private final String testPayload = "{\"key\":\"value\"}";

    @Test
    void saveTbMessageProducerReg_Success() {
        ProducerRegDto producerRegDto = new ProducerRegDto();
        producerRegDto.setLegalEntity(testLegalEntity);
        producerRegDto.setConsumerId(testConsumerId);
        producerRegDto.setNotificationTopic(testTopic);
        producerRegDto.setPayload(testPayload);

        when(snowFlakeIdGenerator.generate()).thenReturn(testId);

        tbMessageProducerRegServiceImpl.saveTbMessageProducerReg(producerRegDto);

        verify(snowFlakeIdGenerator).generate();
        verify(tbMessageProducerRegRepository).saveAndFlush(any());
    }

    @Test
    void saveTbMessageProducerReg_VerifyEntityFields() {

        ProducerRegDto producerRegDto = new ProducerRegDto();
        producerRegDto.setConsumerId(testConsumerId);
        producerRegDto.setLegalEntity(testLegalEntity);
        producerRegDto.setNotificationTopic(testTopic);
        producerRegDto.setPayload(testPayload);

        when(snowFlakeIdGenerator.generate()).thenReturn(testId);

        tbMessageProducerRegServiceImpl.saveTbMessageProducerReg(producerRegDto);
    }
}