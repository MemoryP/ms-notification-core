package com.ocbc.ms.notification.core.feign.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ocbc.ms.notification.core.feign.DTO.FeignRequestHeader;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.nio.charset.StandardCharsets;

public class CoreFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        try {
            byte[] body = template.body();
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();
            ObjectNode emptyNode = objectMapper.createObjectNode();
            if(body != null){
                String s = new String(body, StandardCharsets.UTF_8);
                objectNode.set("body", objectMapper.readTree(s));
            } else {
                objectNode.set("body", emptyNode);
            }

            String encodedHeader = template.headers().get("X-Request-Header").iterator().next();
            FeignRequestHeader header = objectMapper.readValue(encodedHeader, FeignRequestHeader.class);
            objectNode.set("header", objectMapper.valueToTree(header));
            template.body(objectNode.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}