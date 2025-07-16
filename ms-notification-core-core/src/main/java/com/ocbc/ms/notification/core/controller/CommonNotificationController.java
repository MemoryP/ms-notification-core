package com.ocbc.ms.notification.core.controller;

import com.ocbc.ms.cbs.core.dto.CommonResult;
import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.notification.core.converter.AlertTemplateControllerMapper;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.entity.req.*;
import com.ocbc.ms.notification.core.entity.rsp.AlertTemplateRetrieveResponse;
import com.ocbc.ms.notification.core.entity.vo.CheckRequestVo;
import com.ocbc.ms.notification.core.enums.ExceptionEnum;
import com.ocbc.ms.notification.core.service.CommonNoticeService;
import com.ocbc.ms.notification.core.service.IAlertTemplateService;
import com.ocbc.ms.notification.core.service.JwtEncryptionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * send message to staff-notification by kafka
 * @author Zhu yong
 * @version 1.0
 * @since 2025/6/4
 */

@RestController
@Slf4j
public class CommonNotificationController {


    @Autowired
    private CommonNoticeService commonNoticeService;


    /**
     * email notice
     * @return
     */
    @PostMapping(value = "/v1/common/notice/email")
    public CommonResult<Object> sendEmail(@Valid @RequestBody CommNoticeRequest requestBody) {
        CheckRequestVo vo = commonNoticeService.notify(requestBody);
        if(vo.getFlag()){
            return CommonResult.success(vo.getData());
        }else {
            return CommonResult.fail(ExceptionEnum.KAFKA_SENDMSG_ERROR.getCode(),vo.getErrorMsg());
        }
    }

}
