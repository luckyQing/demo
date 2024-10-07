package com.liyulin.spring.event.dto;

import org.springframework.context.ApplicationEvent;

public class SyncEvent extends ApplicationEvent {

    private String name;

    public SyncEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

}