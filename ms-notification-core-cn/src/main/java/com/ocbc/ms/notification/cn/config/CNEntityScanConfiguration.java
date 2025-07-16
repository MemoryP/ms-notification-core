package com.ocbc.ms.notification.cn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "com.ocbc.ms.config.country", havingValue = "CN")
@Configuration
@EntityScan(basePackages = {
    "com.ocbc.ms.notification.cn.entity"})
public class CNEntityScanConfiguration {

}