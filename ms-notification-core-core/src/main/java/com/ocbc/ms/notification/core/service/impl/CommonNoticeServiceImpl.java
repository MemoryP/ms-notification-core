package com.ocbc.ms.notification.core.service.impl;

import cn.hutool.json.JSONUtil;
import com.ocbc.ms.notification.core.client.DynamicFeignClient;
import com.ocbc.ms.notification.core.constant.SendMailConstant;
import com.ocbc.ms.notification.core.entity.vo.CheckRequestVo;
import com.ocbc.ms.notification.core.entity.vo.DomainMetaVo;
import com.ocbc.ms.notification.core.entity.vo.UserInfoVo;
import com.ocbc.ms.notification.core.entity.req.CommNoticeRequest;
import com.ocbc.ms.notification.core.service.CommonNoticeService;
import com.ocbc.ms.notification.core.service.StaffNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CommonNoticeServiceImpl implements CommonNoticeService {

    @Autowired
    private StaffNotificationService notificationService;

    @Value("${com.ocbc.notification.template.notification:}")
    private String emailNotification;
    @Value("${com.ocbc.ms.customer.notification.server:}")
    private String customerNotificationUrl;
    @Value("${com.ocbc.ms.customer.notification.email.api:}")
    private String customerNotificationApi;
    @Value("${com.ocbc.notification.template.request-body:}")
    private String notificationTemplateRequestBody;
    @Value("${com.ocbc.notification.env:}")
    private String notificationEnv;
    @Autowired
    private DynamicFeignClient dynamicFeignClient;

    @Override
    public CheckRequestVo notify(CommNoticeRequest context) {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setId(context.getId());
        userInfoVo.setType(context.getType());
        userInfoVo.setRemark(context.getRemark());
        HashMap<String,Object> extra = new HashMap<>();
        String email =context.getEmail();
        extra.put(SendMailConstant.EMAIL, email);
        String workPhone =context.getWorkPhone();
        extra.put(SendMailConstant.WORKPHONE, workPhone);
        extra.put(SendMailConstant.CC, context.getCc());
        extra.put(SendMailConstant.BCC, context.getBcc());
        userInfoVo.setExtra(extra);
        Map<String, Object> payload = context.getPayload();
        if (payload == null) {
            payload = new HashMap<>();
        }
        payload.put(SendMailConstant.SUBJECT, context.getSubject());
        payload.put(SendMailConstant.MESSAGE, context.getMessage());

        DomainMetaVo domainMetaVo = new DomainMetaVo();
        domainMetaVo.setMaker(userInfoVo);
        domainMetaVo.setPayload(payload);
        // check the input
        CheckRequestVo checkRequestVo = checkRequest(domainMetaVo, emailNotification, email, workPhone);

        String jsonStr = JSONUtil.toJsonStr(domainMetaVo);
        if(!checkRequestVo.getFlag()){
            log.info("check request false,error msg={}, input ={}",checkRequestVo.getErrorMsg(), JSONUtil.toJsonStr(domainMetaVo));
            return checkRequestVo;
        }
        String data;
        if ("true".equals(context.getNotificationType())) {
             data = sendEmailByWebApi(emailNotification, jsonStr, userInfoVo.getId(), userInfoVo.getName(), email);
        } else {
             data = sendEmailByKafka(emailNotification, jsonStr, userInfoVo.getId(), userInfoVo.getName(), email, workPhone, extra, (HashMap<String, Object>) domainMetaVo.getPayload());
        }
        checkRequestVo.setData(data);
        return checkRequestVo;
    }

    /**
     * check vo
     * @param domainMetaVo
     * @return
     */
    CheckRequestVo checkRequest(DomainMetaVo domainMetaVo,String template,String email,String phone){
        CheckRequestVo vo= new CheckRequestVo();
        if(StringUtils.isEmpty(template)){
            vo.setFlag(false);
            vo.setErrorMsg("com.ocbc.notification.template.notification is null");
            return vo;
        }
//        if(StringUtils.isEmpty(email)){
//            vo.setFlag(false);
//            log.info("the email is empty, input ={}", JSONUtil.toJsonStr(domainMetaVo));
//            vo.setErrorMsg("input email is null");
//            return vo;
//        }
//        if(StringUtils.isEmpty(phone)){
//            vo.setFlag(false);
//            log.info("the workPhone is empty, input ={}", JSONUtil.toJsonStr(domainMetaVo));
//            vo.setErrorMsg("input workPhone is null");
//            return vo;
//        }
        return vo;
    }


    /**
     * send comm message by kafka
     * @param template
     * @param msgs
     * @param userId
     * @param userName
     * @param email
     * @param phone
     * @param reqMap
     */
    private String sendEmailByKafka(String template, String msgs, String userId, String userName, String email, String phone,Map<String,Object> reqMap,HashMap<String,Object> payload) {
        HashMap<String, String> params = new HashMap<>();
        params.put("env", notificationEnv);
        params.put("userId", userId);
        params.put("recipient", userName);
        params.put("msgs",msgs);
        params.put("subject",String.valueOf(reqMap.get(SendMailConstant.SUBJECT)));
        return notificationService.sendEmailByKafka(template, params, email, phone, String.valueOf(reqMap.get(SendMailConstant.CC)), String.valueOf(reqMap.get(SendMailConstant.BCC)),payload);
    }

    /**
     * Temp to use email to send the email, will use the sendEmailByKafka once staff-notification is ready
     * @param template
     * @param userName
     * @param email
     */
    private String sendEmailByWebApi(String template,String msgs, String userId, String userName, String email) {
        String payload ="";
        try {
             payload = String.format(notificationTemplateRequestBody, template, notificationEnv, msgs, userId, userName, email);
            dynamicFeignClient.postCall(URI.create(customerNotificationUrl), customerNotificationApi, payload);
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return payload;
    }


    private String getExtraEmail(Object extra) {
        if (extra instanceof Map extraMap) {
            Object email = extraMap.get(SendMailConstant.EMAIL);
            return email == null ? "" : email.toString();
        }
        return "";
    }

    private String getExtraPhone(Object extra) {
        if (extra instanceof Map extraMap) {
            Object email = extraMap.get(SendMailConstant.WORKPHONE);
            return email == null ? "" : email.toString();
        }
        return "";
    }



}
