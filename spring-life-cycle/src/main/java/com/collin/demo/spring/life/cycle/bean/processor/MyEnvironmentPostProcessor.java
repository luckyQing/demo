package com.collin.demo.spring.life.cycle.bean.processor;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        PrintUtil.add("EnvironmentPostProcessor");
    }

}