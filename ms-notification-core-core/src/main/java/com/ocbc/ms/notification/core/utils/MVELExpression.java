package com.ocbc.ms.notification.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mvel2.MVEL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class MVELExpression {

    @Autowired
    ObjectMapper objectMapper;

    public boolean evaluateExpression(String jsonStr, String expression) {
        // 将JSON字符串解析为Map


        Boolean result = false;
        try{
//            Map<String, Object> data = JSON.parseObject(jsonStr, Map.class);
            Map<String, Object> data = objectMapper.readValue(jsonStr, Map.class);
            result = (Boolean) MVEL.eval(expression, data);
        }catch (Exception e){
            log.error("wrong expression");
        }
        return result;
    }

}
