package com.ocbc.ms.notification.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "com.ocbc.ms.config.country", havingValue = "sg")
@ComponentScan(basePackages = {"com.ocbc.ms.notification.sg"})
public class SGPackageScan {

}

