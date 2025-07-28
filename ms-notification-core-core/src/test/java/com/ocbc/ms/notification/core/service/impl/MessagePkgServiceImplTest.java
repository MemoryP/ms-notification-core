package com.ocbc.ms.notification.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.constant.Constants;
import com.ocbc.ms.notification.core.entity.ServiceCodeTemplateEntity;
import com.ocbc.ms.notification.core.entity.dto.ConsumerDto;
import com.ocbc.ms.notification.core.entity.dto.CustomerMessageDto;
import com.ocbc.ms.notification.core.feign.client.MsCustomerClient;
import com.ocbc.ms.notification.core.producer.LocalKafkaProducer;
import com.ocbc.ms.notification.core.repository.ServiceCodeTemplateRepository;
import com.ocbc.ms.notification.core.runner.TopicRunner;
import com.ocbc.ms.notification.core.service.TbMessageConsumerRegService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessagePkgServiceImplTest {

    @Mock
    private TbMessageConsumerRegService tbMessageConsumerRegService;

    @Mock
    private ServiceCodeTemplateRepository serviceCodeTemplateRepository;

    @Mock
    private TbMessageProducerRegServiceImpl tbMessageProducerRegServiceImpl;

    @Mock
    private MsCustomerClient msCustomerClient;

    @Mock
    private LocalKafkaProducer localKafkaProducer;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private Logger log;

    @InjectMocks
    private MessagePkgServiceImpl messagePkgService;

    private final String testServiceCode = "TEST_SERVICE_CODE";
    private final String testLegalEntity = "TEST_LE";
    private final String testConsumerId = "CONSUMER_ID";

    @Test
    void sendMessage_ShouldProcessStaffTopicSuccessfully() throws JsonProcessingException {

        String messageJson = "{\"legalEntity\":\"TEST_LE\",\"serviceCode\":\"TEST_SERVICE_CODE\"}";

        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setLegalEntity(testLegalEntity);
        consumerDto.setServiceCode(testServiceCode);
        consumerDto.setMessage("{\"customerId\": \"1\",\"customerName\": \"1\"}");

        CustomerMessageDto customerMessageDto = new CustomerMessageDto();
        customerMessageDto.setCustomerId("1");
        customerMessageDto.setCustomerName("1");

        ServiceCodeTemplateEntity template = new ServiceCodeTemplateEntity();
        template.setAlterTemplateCode("ALTER_CODE");

        when(objectMapper.readValue(eq(messageJson), eq(ConsumerDto.class))).thenReturn(consumerDto);
        when(tbMessageConsumerRegService.saveTbMessageConsumerReg(consumerDto)).thenReturn(testConsumerId);
        when(serviceCodeTemplateRepository.findById(testServiceCode)).thenReturn(Optional.of(template));
        when(objectMapper.valueToTree(any())).thenReturn(mock(JsonNode.class));
        when(objectMapper.writeValueAsString(any())).thenReturn("serialized-json");
        when(msCustomerClient.getCustomerInfo(any(), any())).thenReturn("");
        when(objectMapper.readValue(eq(consumerDto.getMessage()), eq(CustomerMessageDto.class))).thenReturn(customerMessageDto);

        messagePkgService.sendMessage(messageJson, TopicRunner.getTopicName(Constants.TOPIC_STAFF));

    }

    @Test
    void sendMessage_ShouldLogWarningWhenServiceTemplateNotFound() throws JsonProcessingException {
        String messageJson = "{\"legalEntity\":\"TEST_LE\",\"serviceCode\":\"TEST_SERVICE_CODE\"}";

        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setLegalEntity(testLegalEntity);
        consumerDto.setServiceCode(testServiceCode);

        when(objectMapper.readValue(messageJson, ConsumerDto.class)).thenReturn(consumerDto);
        when(tbMessageConsumerRegService.saveTbMessageConsumerReg(consumerDto)).thenReturn(testConsumerId);
        when(serviceCodeTemplateRepository.findById(testServiceCode)).thenReturn(Optional.empty());

        messagePkgService.sendMessage(messageJson, TopicRunner.getTopicName(Constants.TOPIC_STAFF));

    }

    @Test
    void sendMessage_ShouldLogWarningWhenAlterTemplateCodeIsBlank() throws JsonProcessingException {

        String messageJson = "{\"legalEntity\":\"TEST_LE\",\"serviceCode\":\"TEST_SERVICE_CODE\"}";

        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setLegalEntity(testLegalEntity);
        consumerDto.setServiceCode(testServiceCode);

        ServiceCodeTemplateEntity template = new ServiceCodeTemplateEntity();
        template.setAlterTemplateCode("");

        when(objectMapper.readValue(messageJson, ConsumerDto.class)).thenReturn(consumerDto);
        when(tbMessageConsumerRegService.saveTbMessageConsumerReg(consumerDto)).thenReturn(testConsumerId);
        when(serviceCodeTemplateRepository.findById(testServiceCode)).thenReturn(Optional.of(template));

        messagePkgService.sendMessage(messageJson, TopicRunner.getTopicName(Constants.TOPIC_STAFF));

    }

    @Test
    void sendMessage_ShouldThrowBizExceptionWhenJsonInvalid() throws JsonProcessingException {
        String invalidJson = "invalid-json";
        when(objectMapper.readValue(invalidJson, ConsumerDto.class))
                .thenThrow(new JsonProcessingException("Invalid JSON") {});

        assertThrows(BizException.class, () ->
                messagePkgService.sendMessage(invalidJson, TopicRunner.getTopicName(Constants.TOPIC_STAFF)));
    }

    @Test
    void sendMessage_ShouldSkipProcessingForNonStaffTopic() throws JsonProcessingException {
        // Arrange
        String messageJson = "{\"legalEntity\":\"TEST_LE\",\"serviceCode\":\"TEST_SERVICE_CODE\"}";

        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setLegalEntity(testLegalEntity);
        consumerDto.setServiceCode(testServiceCode);

        when(objectMapper.readValue(messageJson, ConsumerDto.class)).thenReturn(consumerDto);
        when(tbMessageConsumerRegService.saveTbMessageConsumerReg(consumerDto)).thenReturn(testConsumerId);

        messagePkgService.sendMessage(messageJson, "customer");

    }

    @Test
    void buildStaffMessage_ShouldBuildCorrectDtoStructure(){
        ConsumerDto consumerDto = new ConsumerDto();
        consumerDto.setLegalEntity(testLegalEntity);
        consumerDto.setServiceCode(testServiceCode);

        ServiceCodeTemplateEntity template = new ServiceCodeTemplateEntity();
        template.setAlterTemplateCode("ALTER_CODE");

        JsonNode mockNode = mock(JsonNode.class);
        when(objectMapper.valueToTree(consumerDto)).thenReturn(mockNode);

        messagePkgService.buildStaffMessage(consumerDto, template);
    }
}