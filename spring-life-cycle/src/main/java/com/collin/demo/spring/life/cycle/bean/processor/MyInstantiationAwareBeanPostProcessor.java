package com.collin.demo.spring.life.cycle.bean.processor;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Slf4j
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private boolean skipped1 = false;
    private boolean skipped2 = false;
    private boolean skipped3 = false;
    private boolean skipped4 = false;

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (!skipped1) {
            skipped1 = true;
            log.error("----> InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation");
            PrintUtil.add("InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation");
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (!skipped2) {
            skipped2 = true;
            log.error("----> InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation");
            PrintUtil.add("InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (!skipped3) {
            skipped3 = true;
            log.error("----> InstantiationAwareBeanPostProcessor#postProcessProperties");
            PrintUtil.add("InstantiationAwareBeanPostProcessor#postProcessProperties");
        }
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (!skipped4) {
            skipped4 = true;
            log.error("----> InstantiationAwareBeanPostProcessor#postProcessPropertyValues");
            PrintUtil.add("InstantiationAwareBeanPostProcessor#postProcessPropertyValues");
        }
        return pvs;
    }

}