package com.ocbc.ms.notification.core.client;

import com.ocbc.ms.cbs.core.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.URI;

/**
 * @author tang tong (A5152682)
 * @date 2023/9/21
 **/
@FeignClient(name = "DynamicFeignClient", url = "EMPTY")
public interface DynamicFeignClient {

    /**
     * dynamic remote call
     *
     * @param uri
     * @param path
     * @param json
     * @return
     */
    @PostMapping(value = "{path}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    CommonResult<Object> postCall(URI uri, @PathVariable("path") String path, @RequestBody(required = false) String json);

    /**
     * dynamic remote call
     *
     * @param uri
     * @param path
     * @param json
     * @return
     */
    @PostMapping(value = "{path}", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    CommonResult<Object> postCall(URI uri,
                                  @PathVariable("path") String path,
                                  @RequestHeader("x-teller-no") String tellerNo,
                                  @RequestHeader("x-checker-no") String checkerNo,
                                  @RequestHeader("x-transaction-no") String txNo,
                                  @RequestBody String json);

}
