package com.ocbc.ms.notification.core.feign.client;

import com.ocbc.ms.notification.core.feign.DTO.customer.CustDetailQueryResponse;
import com.ocbc.ms.notification.core.feign.handler.CoreFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "ms-cust", url = "${cbs.customer.feign.client}", configuration = CoreFeignInterceptor.class)
public interface MsCustomerClient {

    @PostMapping("v2/customer/retrieve")
    CustDetailQueryResponse getCustomerInfo(@RequestHeader("X-Request-Header") String encodedHeader, String request);
}
