package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

public class MyBootstrapRegistryInitializer implements BootstrapRegistryInitializer {

    @Override
    public void initialize(BootstrapRegistry registry) {
        PrintUtil.add("BootstrapRegistryInitializer");
    }

}