package com.ocbc.ms.notification.core.controller;

import com.ocbc.ms.cbs.core.dto.CommonResult;
import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.notification.core.converter.CustTemplateControllerMapper;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.req.CustTemplateInqueryRequest;
import com.ocbc.ms.notification.core.entity.req.CustTemplateSaveRequest;
import com.ocbc.ms.notification.core.entity.rsp.CustTemplateRetrieveResponse;
import com.ocbc.ms.notification.core.service.ICustTemplateService;
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
public class CustTemplateController {

    private final CustTemplateControllerMapper mapper = CustTemplateControllerMapper.instance;

    @Autowired
    private JwtEncryptionService jwtEncryptionService;


    @Autowired
    private ICustTemplateService custTemplateService;

    /**
     * search for staff-notification templates
     * @param request
     * @return
     */
    @PostMapping(value = "/v1/customer-template/inquiry")
    public CommonResult<PageDTO<CustTemplateDTO>> list(@Valid @RequestBody CustTemplateInqueryDTO request) {
        PageDTO<CustTemplateDTO> dtoList = custTemplateService.page(mapper.convert(request));
        return CommonResult.success(dtoList);
    }

    /**
     * retrieve staff-notification template detail
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/customer-template/retrieve")
    public CommonResult<CustTemplateRetrieveResponse> retrieve(@Valid @RequestBody CustTemplateInqueryRequest requestBody) {
        CustTemplateDTO dto = custTemplateService.retrieve(requestBody.getTemplateId(), requestBody.getLanguage());
        return CommonResult.success(mapper.convert(dto));
    }

    /**
     * save staff-notification template
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/v1/customer-template/save")
    public CommonResult<Object> save(@Valid @RequestBody CustTemplateSaveRequest requestBody, @RequestHeader HttpHeaders headers) {
        String userId = jwtEncryptionService.resolveUserIdFromJWT(headers);
        CustTemplateDTO dto = mapper.convert(requestBody);
        dto.setCreatedBy(userId);
        dto.setLastUpdatedBy(userId);
        dto.setCreatedTime(LocalDateTime.now());
        dto.setLastUpdatedTime(LocalDateTime.now());
        custTemplateService.save(dto);
        return CommonResult.success();
    }

    @PostMapping(value = "/v1/customer-template/update")
    public CommonResult<Object> update(@Valid @RequestBody CustTemplateSaveRequest requestBody, @RequestHeader HttpHeaders headers) {
        String userId = jwtEncryptionService.resolveUserIdFromJWT(headers);
        CustTemplateDTO dto = mapper.convert(requestBody);
        dto.setLastUpdatedBy(userId);
        custTemplateService.update(dto);
        return CommonResult.success();
    }

    /**
     * search staff-notification template's tag list
     * @return
     */
    @PostMapping(value = "/v1/customer-template/meta")
    public CommonResult<AlertTemplateMetaDto> listTags() {
        return CommonResult.success(custTemplateService.listMeta());
    }

}
