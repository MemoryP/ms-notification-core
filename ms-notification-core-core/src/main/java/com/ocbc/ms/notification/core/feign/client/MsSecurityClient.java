package com.ocbc.ms.notification.core.feign.client;

import com.ocbc.ms.notification.core.feign.DTO.security.StaffContactRequest;
import com.ocbc.ms.notification.core.feign.DTO.security.StaffContactResponse;
import com.ocbc.ms.notification.core.feign.handler.CoreFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "ms-security", url = "${cbs.security.feign.client}", configuration = CoreFeignInterceptor.class)
public interface MsSecurityClient {

    @PostMapping("v1/staff-security-cbs/internal/users/contact")
    List<StaffContactResponse> getStaffContactList(@RequestHeader("X-Request-Header") String encodedHeader, StaffContactRequest request);
}
