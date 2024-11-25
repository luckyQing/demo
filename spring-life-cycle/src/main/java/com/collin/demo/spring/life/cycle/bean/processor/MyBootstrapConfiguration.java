package com.collin.demo.spring.life.cycle.bean.processor;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBootstrapConfiguration implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        PrintUtil.add("BootstrapConfiguration InitializingBean");
    }

    @Bean
    public PrintUtil printUtil() {
        PrintUtil.add("BootstrapConfiguration PrintUtil");
        return new PrintUtil();
    }

}