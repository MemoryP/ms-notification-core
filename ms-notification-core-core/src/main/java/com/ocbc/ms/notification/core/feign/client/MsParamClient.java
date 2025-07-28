package com.ocbc.ms.notification.core.feign.client;

import com.ocbc.ms.cbs.core.dto.CommonResult;
import com.ocbc.ms.notification.core.feign.DTO.param.ParameterCodeRequest;
import com.ocbc.ms.notification.core.feign.handler.CoreFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "ms-param", url = "${cbs.param.feign.client}", configuration = CoreFeignInterceptor.class)
public interface MsParamClient {

    @PostMapping("v1/parameter/list")
    CommonResult<List<Object>> getCommonParameterList(@RequestHeader("X-Request-Header") String encodedHeader, ParameterCodeRequest param);
}
