package com.ocbc.ms.notification.core.service;

import java.util.HashMap;

public interface StaffNotificationService {
    String sendEmailByKafka(String template, HashMap<String, String> params, String email, String phone, String cc, String bcc,HashMap<String, Object> payload);

}
