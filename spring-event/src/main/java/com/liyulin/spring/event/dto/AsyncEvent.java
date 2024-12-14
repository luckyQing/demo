package com.liyulin.spring.event.dto;

import org.springframework.context.ApplicationEvent;

public class AsyncEvent extends ApplicationEvent {

    private String name;

    public AsyncEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

}