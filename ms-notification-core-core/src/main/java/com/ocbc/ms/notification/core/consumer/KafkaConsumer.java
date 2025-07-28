package com.ocbc.ms.notification.core.consumer;

import com.ocbc.ms.notification.core.service.impl.MessagePkgServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MessagePkgServiceImpl messagePkgService;

//    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        messagePkgService.sendMessage(message, topic);
    }
}
