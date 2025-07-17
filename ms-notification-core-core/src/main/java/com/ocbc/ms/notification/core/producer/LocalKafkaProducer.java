package com.ocbc.ms.notification.core.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author WeiJiaXu
 * @date 2024/8/14 10:24
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LocalKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
            kafkaTemplate.send(topic, message);
    }
}
