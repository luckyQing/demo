package com.demo.collin.rocketmq.service.impl;

import com.demo.collin.rocketmq.dto.BaseTransactionMsgDTO;
import com.demo.collin.rocketmq.service.ILocalTransactionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LocalTransactionStrategyFactory implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;
    private Map<String, ILocalTransactionStrategy> strategies = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, ILocalTransactionStrategy> beanOfType = applicationContext.getBeansOfType(ILocalTransactionStrategy.class);
        if (MapUtils.isEmpty(beanOfType)) {
            return;
        }

        beanOfType.forEach((beanName, bean) -> strategies.put(bean.getTransactionCode().getCode(), bean));
    }


    public <T extends BaseTransactionMsgDTO> boolean execute(T context) {
        ILocalTransactionStrategy<T> strategy = strategies.get(context.getTransactionCode().getCode());
        if (strategy == null) {
            log.error("未找到对应的本地事务处理策略，transactionCode: {}", context.getTransactionCode().getCode());
            return false;
        }

        return strategy.execute(context);
    }

}