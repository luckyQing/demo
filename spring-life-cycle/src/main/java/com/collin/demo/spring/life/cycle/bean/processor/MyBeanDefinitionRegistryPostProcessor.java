package com.collin.demo.spring.life.cycle.bean.processor;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.error("----> BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry");
        PrintUtil.add("BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.error("----> BeanDefinitionRegistryPostProcessor#postProcessBeanFactory");
        PrintUtil.add("BeanDefinitionRegistryPostProcessor#postProcessBeanFactory");
    }

}