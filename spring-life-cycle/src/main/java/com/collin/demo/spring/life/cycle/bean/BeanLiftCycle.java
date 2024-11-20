package com.collin.demo.spring.life.cycle.bean;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanLiftCycle implements BeanDefinitionRegistryPostProcessor, InitializingBean, BeanFactoryPostProcessor, BeanFactoryAware,
        EnvironmentAware, ApplicationContextAware {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.error("----> BeanLiftCycle@BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry");
        PrintUtil.add("BeanLiftCycle@BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.error("----> BeanLiftCycle@BeanDefinitionRegistryPostProcessor#postProcessBeanFactory");
        PrintUtil.add("BeanLiftCycle@BeanDefinitionRegistryPostProcessor#postProcessBeanFactory");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.error("---->BeanLiftCycle@InitializingBean#afterPropertiesSet");
        PrintUtil.add("BeanLiftCycle@InitializingBean#afterPropertiesSet");
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.error("---->BeanLiftCycle@EnvironmentAware#setEnvironment");
        PrintUtil.add("BeanLiftCycle@EnvironmentAware#setEnvironment");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.error("---->BeanLiftCycle@BeanFactoryAware#setBeanFactory");
        PrintUtil.add("BeanLiftCycle@BeanFactoryAware#setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.error("---->BeanLiftCycle@ApplicationContextAware#setApplicationContext");
        PrintUtil.add("BeanLiftCycle@ApplicationContextAware#setApplicationContext");
    }
}