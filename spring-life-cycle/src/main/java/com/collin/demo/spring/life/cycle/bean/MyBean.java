package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBean {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanTest beanTest() {
        return new BeanTest();
    }


    @Slf4j
    static class BeanTest {
        public void initMethod() {
            log.error("----> initMethod");
            PrintUtil.add("initMethod");
        }

        public void destroyMethod() {
            log.error("----> destroyMethod");
            PrintUtil.add("destroyMethod");
        }
    }

}