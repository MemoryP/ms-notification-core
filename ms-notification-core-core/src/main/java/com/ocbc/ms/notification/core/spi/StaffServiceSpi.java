package com.ocbc.ms.notification.core.spi;

import com.ocbc.ms.notification.core.entity.req.StaffReqDto;
import com.ocbc.ms.notification.core.entity.rsp.StaffRspDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "staff-core", url="${client.service-host.staff-core}")
public interface StaffServiceSpi {

    @PostMapping("/v1/staff-security-cbs/user/list")
    List<StaffRspDto> queryStaffInfo(@RequestBody StaffReqDto staffReqDto);

}
