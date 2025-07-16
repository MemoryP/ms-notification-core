package com.ocbc.ms.notification.sg.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@ConditionalOnProperty(name = "com.ocbc.ms.config.country", havingValue = "SG")
@Configuration
@EntityScan(basePackages = {
    "com.ocbc.ms.notification.sg.entity"})
public class SGEntityScanConfiguration {

}