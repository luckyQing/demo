package com.collin.demo.spring.life.cycle.bean.processor;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        log.error("----> MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition");
        PrintUtil.add("MergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition");
    }

    @Override
    public void resetBeanDefinition(String beanName) {
        log.error("----> MergedBeanDefinitionPostProcessor#resetBeanDefinition");
        PrintUtil.add("MergedBeanDefinitionPostProcessor#resetBeanDefinition");
    }
}
