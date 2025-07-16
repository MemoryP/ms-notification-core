package com.ocbc.ms.notification.cn.config;

import com.ocbc.ms.core.exception.ErrorResponse;
import com.ocbc.ms.core.exception.ErrorResponseV1;
import com.ocbc.ms.notification.cn.constants.Constants;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseBodySupport implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return body;
        }
        if(body.getClass().getPackage().getName().matches("com.ocbc.ms..*."+ Constants.CN_COUNTRY_CODE + "..*")){
            if(body instanceof ErrorResponseV1) {
                return CommonRes.builder()
                    .head(CommonRes.getStandardHead(((ErrorResponseV1) body).getErrorCode()))
                    .body(CommonRes.Body.builder()
                        .rtnLst(null)
                        .build())
                    .build();
            }
            if (body instanceof ErrorResponse) {
                return CommonRes.builder()
                    .head(CommonRes.getStandardHead("DAS.SYS.01"))
                    .body(CommonRes.Body.builder()
                        .rtnLst(null)
                        .build())
                    .build();
            }
        }
        return body;
    }
}
