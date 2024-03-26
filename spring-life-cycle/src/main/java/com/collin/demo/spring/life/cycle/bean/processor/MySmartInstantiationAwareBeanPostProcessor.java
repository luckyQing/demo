package com.collin.demo.spring.life.cycle.bean.processor;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

@Slf4j
@Component
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    private boolean skipped1 = false;
    private boolean skipped2 = false;
    private boolean skipped3 = false;

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        if (!skipped1) {
            skipped1 = true;
            log.error("----> SmartInstantiationAwareBeanPostProcessor#predictBeanType");
            PrintUtil.add("SmartInstantiationAwareBeanPostProcessor#predictBeanType");
        }
        return null;
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        if (!skipped2) {
            skipped2 = true;
            log.error("----> SmartInstantiationAwareBeanPostProcessor#determineCandidateConstructors");
            PrintUtil.add("SmartInstantiationAwareBeanPostProcessor#determineCandidateConstructors");
        }
        return null;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if (!skipped3) {
            skipped3 = true;
            log.error("----> SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference");
            PrintUtil.add("SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference");
        }
        return bean;
    }

}