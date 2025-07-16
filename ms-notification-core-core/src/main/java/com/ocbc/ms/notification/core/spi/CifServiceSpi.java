package com.ocbc.ms.notification.core.spi;

import com.ocbc.ms.notification.core.entity.req.CustomerReqDto;
import com.ocbc.ms.notification.core.entity.rsp.CustomerRspDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cif-core", url="${client.service-host.cif-core}")
public interface CifServiceSpi {

    @PostMapping("/v2/customer/retrieve")
    CustomerRspDto queryCustomerInfo(@RequestBody CustomerReqDto customerReqDto);

}
