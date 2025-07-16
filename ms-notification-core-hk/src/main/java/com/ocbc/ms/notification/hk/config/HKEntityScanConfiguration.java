package com.ocbc.ms.notification.hk.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@ConditionalOnProperty(name = "com.ocbc.ms.config.country", havingValue = "HK")
@Configuration
@EntityScan(basePackages = {
    "com.ocbc.ms.notification.hk.entity"})
public class HKEntityScanConfiguration {

}
