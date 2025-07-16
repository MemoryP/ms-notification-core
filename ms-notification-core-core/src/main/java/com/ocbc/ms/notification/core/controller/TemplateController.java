package com.ocbc.ms.notification.core.controller;

import com.ocbc.ms.cbs.core.dto.CommonResult;
import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.notification.core.converter.AlertTemplateControllerMapper;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.entity.req.AlertTemplateInqueryRequest;
import com.ocbc.ms.notification.core.entity.req.AlertTemplateRetrieveRequest;
import com.ocbc.ms.notification.core.entity.req.AlertTemplateSaveRequest;
import com.ocbc.ms.notification.core.entity.rsp.AlertTemplateRetrieveResponse;
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
 * @author A5130904
 * @version 1.0
 * @since 2024/7/4
 */

@RestController
@Slf4j
public class TemplateController {

    private final AlertTemplateControllerMapper mapper = AlertTemplateControllerMapper.instance;

    @Autowired
    private JwtEncryptionService jwtEncryptionService;


    @Autowired
    private IAlertTemplateService alertTemplateService;

    /**
     * search for staff-notification templates
     * @param request
     * @return
     */
    @PostMapping(value = "/v1/alert-template/inquiry")
    public CommonResult<PageDTO<AlertTemplateDTO>> list(@Valid @RequestBody AlertTemplateInqueryRequest request) {
        log.info("Inquiry template: {}", request);
        PageDTO<AlertTemplateDTO> dtoList = alertTemplateService.page(mapper.convert(request));
        return CommonResult.success(dtoList);
    }

    /**
     * retrieve staff-notification template detail
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/alert-template/retrieve")
    public CommonResult<AlertTemplateRetrieveResponse> retrieve(@Valid @RequestBody AlertTemplateRetrieveRequest requestBody) {
        AlertTemplateDTO dto = alertTemplateService.retrieve(requestBody.getTemplateId(), requestBody.getLanguage());
        return CommonResult.success(mapper.convert(dto));
    }

    /**
     * save staff-notification template
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/alert-template/save")
    public CommonResult<Object> save(@Valid @RequestBody AlertTemplateSaveRequest requestBody, @RequestHeader HttpHeaders headers) {
        String userId = jwtEncryptionService.resolveUserIdFromJWT(headers);
        AlertTemplateDTO dto = mapper.convert(requestBody);
        dto.setCreatedBy(userId);
        dto.setLastUpdatedBy(userId);
        dto.setCreatedTime(LocalDateTime.now());
        dto.setLastUpdatedTime(LocalDateTime.now());
        alertTemplateService.save(dto);
        return CommonResult.success();
    }

    @PostMapping(value = "/v1/alert-template/update")
    public CommonResult<Object> update(@Valid @RequestBody AlertTemplateSaveRequest requestBody, @RequestHeader HttpHeaders headers) {
        log.info("Update template: {}", requestBody);
        String userId = jwtEncryptionService.resolveUserIdFromJWT(headers);
        AlertTemplateDTO dto = mapper.convert(requestBody);
        dto.setLastUpdatedBy(userId);
        alertTemplateService.update(dto);
        return CommonResult.success();
    }

    /**
     * search staff-notification template's tag list
     * @return
     */
    @PostMapping(value = "/v1/alert-template/meta")
    public CommonResult<AlertTemplateMetaDto> listTags() {
        return CommonResult.success(alertTemplateService.listMeta());
    }

}
