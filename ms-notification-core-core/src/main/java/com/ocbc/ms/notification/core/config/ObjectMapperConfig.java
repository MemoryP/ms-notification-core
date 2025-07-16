package com.ocbc.ms.notification.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper defaultMapperCreator() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()).registerModule(new ParameterNamesModule()).registerModules(ObjectMapper.findModules());
        //是否允许使用注释
//        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        //字段允许去除引号
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许单引号
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //允许转义字符
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        //严格重复检测
//        mapper.configure(JsonParser.Feature.STRICT_DUPLICATE_DETECTION, true);
        //不检测失败字段映射
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //时间字段输出时间戳
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        //时间输出为毫秒而非纳秒
        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        //空对象不出错
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //时间读取为毫秒而非纳秒
        mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        //不输出空值字段
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
//        mapper.setTimeZone(systemTimeZone);

//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        return mapper;
    }


}
