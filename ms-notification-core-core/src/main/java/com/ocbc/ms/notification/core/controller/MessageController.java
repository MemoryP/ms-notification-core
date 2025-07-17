package com.ocbc.ms.notification.core.controller;

import com.ocbc.ms.notification.core.service.MessagePkgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {

    private final MessagePkgService messagePkgService;

//    @Async("taskExecutor")
    @PostMapping("/sendM")
    public void messageHand(@RequestBody Map<String, String> request){
        String message = request.get("message");
        String topic = request.get("topic");
        messagePkgService.sendMessage(message, topic);
    }
//    @Async("taskExecutor")
//    public void messageHand(String message, String topic){
//        messagePkgService.sendMessage(message, topic);
//    }

}
