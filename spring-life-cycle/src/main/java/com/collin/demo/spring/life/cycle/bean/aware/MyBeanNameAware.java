package com.collin.demo.spring.life.cycle.bean.aware;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBeanNameAware implements BeanNameAware {

    @Override
    public void setBeanName(String name) {
        log.error("----> BeanNameAware");
        PrintUtil.add("BeanNameAware");
    }

}