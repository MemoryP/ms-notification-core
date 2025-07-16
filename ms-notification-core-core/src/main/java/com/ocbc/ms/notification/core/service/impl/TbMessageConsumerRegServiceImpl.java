package com.ocbc.ms.notification.core.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.ms.notification.core.entity.TbMessageConsumerReg;
import com.ocbc.ms.notification.core.entity.dto.ConsumerDto;
import com.ocbc.ms.notification.core.repository.TbMessageConsumerRegRepository;
import com.ocbc.ms.notification.core.service.TbMessageConsumerRegService;
import com.ocbc.ms.notification.core.utils.SnowFlakeIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TbMessageConsumerRegServiceImpl implements TbMessageConsumerRegService {

    private final TbMessageConsumerRegRepository tbMessageConsumerRegRepository;

    private final SnowFlakeIdGenerator snowFlakeIdGenerator;

    private final ObjectMapper objectMapper;

    @Override
    public String saveTbMessageConsumerReg(ConsumerDto consumerDto) {
        try {
            TbMessageConsumerReg tbMessageConsumerReg = new TbMessageConsumerReg();
            tbMessageConsumerReg.setId(snowFlakeIdGenerator.generate())
                    .setLegalEntity(consumerDto.getLegalEntity())
                    .setServiceCode(consumerDto.getServiceCode())
                    .setServiceSubCode(consumerDto.getServiceSubCode())
                    .setPayload(objectMapper.writeValueAsString(consumerDto))
                    .setCreatedTime(new Date())
                    .setUpdatedTime(new Date());
            tbMessageConsumerRegRepository.saveAndFlush(tbMessageConsumerReg);
            return tbMessageConsumerReg.getId();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
