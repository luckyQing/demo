package com.liyulin.spring.event.service;

import com.liyulin.spring.event.dto.SyncEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class EventPublishService implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void sendSyncEvent() {
        applicationContext.publishEvent(new SyncEvent(this, "test sync event"));
    }

}