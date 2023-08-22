package com.liyulin.demo.openfeign.conguration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignUrlRuleConfiguration {

    @Bean
    Logger.Level feignLoggerLeave() {
        return Logger.Level.FULL;
    }

}