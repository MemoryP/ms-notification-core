package com.ocbc.ms.notification.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.ms.notification.core.entity.dto.ConsumerDto;
import com.ocbc.ms.notification.core.repository.TbMessageConsumerRegRepository;
import com.ocbc.ms.notification.core.utils.SnowFlakeIdGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TbMessageConsumerRegServiceImplTest {

    @Mock
    private SnowFlakeIdGenerator snowFlakeIdGenerator;

    @Mock
    private TbMessageConsumerRegRepository tbMessageConsumerRegRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TbMessageConsumerRegServiceImpl tbMessageConsumerRegServiceImpl;

    @Test
    void saveTbMessageConsumerReg_Success() throws JsonProcessingException {
        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setLegalEntity("TEST_LEGAL_ENTITY");
        consumerDto.setServiceCode("TEST_SERVICE_CODE");
        consumerDto.setServiceSubCode("TEST_SERVICE_SUB_CODE");

        when(snowFlakeIdGenerator.generate()).thenReturn("SNOWFLAKE_ID_123");
        String payloadJson = "{\"legalEntity\":\"TEST_LEGAL_ENTITY\"}";
        when(objectMapper.writeValueAsString(consumerDto)).thenReturn(payloadJson);
        when(tbMessageConsumerRegRepository.saveAndFlush(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        String result = tbMessageConsumerRegServiceImpl.saveTbMessageConsumerReg(consumerDto);

        assertEquals("SNOWFLAKE_ID_123", result);

        verify(snowFlakeIdGenerator).generate();
        verify(objectMapper).writeValueAsString(consumerDto);
        verify(tbMessageConsumerRegRepository).saveAndFlush(any());
    }

    @Test
    void saveTbMessageConsumerReg_JsonProcessingException() throws JsonProcessingException {
        ConsumerDto consumerDto = new ConsumerDto();

        when(objectMapper.writeValueAsString(any())).thenThrow(new JsonProcessingException("JSON error") {
        });

        String result = tbMessageConsumerRegServiceImpl.saveTbMessageConsumerReg(consumerDto);
        assertNull(result);
    }
}