package com.ocbc.ms.notification.core.feign.param.client;

import com.ocbc.ms.notification.core.entity.dto.ConsumerDto;
import com.ocbc.ms.notification.core.feign.handler.CoreFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "ms-cust", url = "${cbs.customer.feign.client}", configuration = CoreFeignInterceptor.class)
public interface MsCustomerClient {

    @PostMapping("v2/getCustomerInfo")
    void getCustomerInfo(@RequestHeader("X-Request-Header") String encodedHeader, String customerId);
}
