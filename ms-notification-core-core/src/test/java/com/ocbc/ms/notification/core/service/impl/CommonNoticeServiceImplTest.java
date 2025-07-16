package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.cbs.core.notification.dto.NotificationProperties;
import com.ocbc.ms.notification.core.client.DynamicFeignClient;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.req.CommNoticeRequest;
import com.ocbc.ms.notification.core.entity.vo.CheckRequestVo;
import com.ocbc.ms.notification.core.entity.vo.UserInfoVo;
import com.ocbc.ms.notification.core.service.StaffNotificationService;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CommonNoticeServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CommonNoticeServiceImplTest.class);
    @InjectMocks
    private CommonNoticeServiceImpl commonNoticeService;

    @InjectMocks
    private StaffNotificationServiceImpl staffNotificationService;


    @Mock
    private StaffNotificationService notificationService;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private NotificationProperties notificationProperties;

    @Mock
    private DynamicFeignClient dynamicFeignClient;

    @BeforeEach
    public void setUp() throws Exception {
        Field field = CommonNoticeServiceImpl.class.getDeclaredField("emailNotification");
        field.setAccessible(true);
        field.set(commonNoticeService, "NOTIFICATION-COMM|EMAIL|EN");

        Field customerNotificationUrl = CommonNoticeServiceImpl.class.getDeclaredField("customerNotificationUrl");
        customerNotificationUrl.setAccessible(true);
        customerNotificationUrl.set(commonNoticeService, "https://customer-notification-hk.apps.dev.ocbc.com");

        Field customerNotificationApi = CommonNoticeServiceImpl.class.getDeclaredField("customerNotificationApi");
        customerNotificationApi.setAccessible(true);
        customerNotificationApi.set(commonNoticeService, "/v1/cust-notification/send");

        Field notificationTemplateRequestBody = CommonNoticeServiceImpl.class.getDeclaredField("notificationTemplateRequestBody");
        notificationTemplateRequestBody.setAccessible(true);
        notificationTemplateRequestBody.set(commonNoticeService, "{\"topic\": \"notification-default\",\"eventMessageVO\": {\"type\": \"%s\",\"payload\": {\"env\": \"%s\",\"msgs\": \"%s\" \"userId\":\"%s\", \"userName\":\"%s\",\"notificationData\": {\"User-C2-88ed-cb6d169f34ec\": {\"user\": {\"id\": \"User-C2-88ed-cb6d169f34ec\",\"recipientInfo\": {\"email\": \"%s\"}},\"channels\": {\"EMAIL\": {\"type\": \"EMAIL\",\"enabled\": true}}}}}}}");

        Field notificationEnv = CommonNoticeServiceImpl.class.getDeclaredField("notificationEnv");
        notificationEnv.setAccessible(true);
        notificationEnv.set(commonNoticeService, "SIT");
    }

    @Test
    public void testNotify() {
        // given
        CommNoticeRequest requestBody = new CommNoticeRequest();
        requestBody.setName("22");
        requestBody.setId("22");
        requestBody.setType("22");
        requestBody.setMessage("msg");
        requestBody.setCc("");
        requestBody.setEmail("extheard@qq.com");
        requestBody.setRemark("zzzz");
        requestBody.setSubject("msg");
        requestBody.setWorkPhone("123456789123");

//        Map<String,Object> ex = new HashMap<>();
//        requestBody.setPayload(ex);
        requestBody.setNotificationType("false");

        when(notificationService.sendEmailByKafka(any(String.class), any(HashMap.class), any(String.class), any(String.class), any(String.class), any(String.class), any(HashMap.class))).thenReturn("ok");
        // when
        CheckRequestVo notify = commonNoticeService.notify(requestBody);
        log.info("notify: {}", notify);
    }

    @Test
    public void testNotify2() {
        // given
        CommNoticeRequest requestBody = new CommNoticeRequest();
        requestBody.setName("22");
        requestBody.setId("22");
        requestBody.setType("22");
        requestBody.setMessage("msg");
        requestBody.setCc("");
        requestBody.setEmail("extheard@qq.com");
        requestBody.setRemark("zzzz");
        requestBody.setSubject("msg");
        requestBody.setWorkPhone("123456789123");

        requestBody.setNotificationType("true");

        when(dynamicFeignClient.postCall(any(URI.class), any(String.class), any(String.class))).thenReturn(null);
        // when
        CheckRequestVo notify = commonNoticeService.notify(requestBody);
        log.info("notify: {}", notify);
    }


    @Test
    public void testSendEmailByKafka() {
        // given
        HashMap<String,Object> payload = new HashMap<>();
        payload.put("subject","textheard");
        payload.put("message","msg");
        // when
        HashMap<String, String> params = new HashMap<>();
        params.put("env", "SIT");
        params.put("userId", "1");
        params.put("recipient", "zhuyong");
        params.put("msgs","I want get more money");
        params.put("subject","go to huanggong");

        CompletableFuture<SendResult<String, String>> future = createCompletedFuture();

        when(notificationProperties.getTopic()).thenReturn("OCHK_OC_CBS_CMM_NTF_P1");
        when(notificationProperties.isEnabled()).thenReturn(true);
        when(kafkaTemplate.send(any(String.class), any(String.class))).thenReturn(future);

        String s = staffNotificationService.sendEmailByKafka("NOTIFICATION-COMM|EMAIL|EN", params, "textheard@qq.com", "18113121212", "", "", payload);
        log.info("s: {}", s);

    }

    public static CompletableFuture<SendResult<String, String>> createCompletedFuture() {
        // 构造一个假的 RecordMetadata
        RecordMetadata metadata = new RecordMetadata(
                new org.apache.kafka.common.TopicPartition("test-topic", 0),
                0, 0, System.currentTimeMillis(), Long.valueOf(0), 0, 0
        );

        // 构造一个 SendResult
        SendResult<String, String> sendResult = new SendResult<>(null, metadata);

        // 返回一个已完成的 CompletableFuture
        return CompletableFuture.completedFuture(sendResult);
    }


}
