package com.collin.demo.spring.life.cycle.bean.aware;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBeanClassLoaderAware implements BeanClassLoaderAware {
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.error("----> BeanClassLoaderAware");
        PrintUtil.add("BeanClassLoaderAware");
    }
}