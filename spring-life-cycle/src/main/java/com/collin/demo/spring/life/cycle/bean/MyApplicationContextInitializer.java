package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        PrintUtil.add("ApplicationContextInitializer");
    }

}