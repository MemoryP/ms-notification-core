package com.ocbc.ms.notification.core.controller;

import com.ocbc.ms.notification.core.producer.LocalKafkaProducer;
import com.ocbc.ms.notification.core.service.TbMessageConditionDefService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WeiJiaXu
 * @date 2024/8/14 10:25
 */
@RestController
public class KafkaProducerController {


    @Resource
    private LocalKafkaProducer localKafkaProducer;

    @Resource
    private TbMessageConditionDefService tbMessageConditionDefService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody String message) {
        localKafkaProducer.sendMessage("test-topic",message);
        return "success";
    }
}
