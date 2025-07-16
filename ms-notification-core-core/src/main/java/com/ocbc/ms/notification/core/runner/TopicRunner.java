package com.ocbc.ms.notification.core.runner;

import com.ocbc.ms.notification.core.constant.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Order(value = 2)
public class TopicRunner implements ApplicationRunner {

    @Value("${spring.kafka.producer.topicCustomer}")
    private String topicCustomer;
    @Value("${spring.kafka.producer.topicStaff}")
    private String topicStaff;
    @Value("${spring.kafka.producer.topicStaffTask}")
    private String topicStaffTask;

    @Getter
    @Setter
    private static Map<String, String> topicMap = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Init topic data");
        topicMap.put(Constants.TOPIC_CUSTOMER, topicCustomer);
        topicMap.put(Constants.TOPIC_STAFF, topicStaff);
        topicMap.put(Constants.TOPIC_STAFF_TASK, topicStaffTask);
    }

    public static String getTopicName(String topicName){
        return StringUtils.isEmpty(topicName)||topicMap.get(topicName)==null?"":topicMap.get(topicName);
    }
}
