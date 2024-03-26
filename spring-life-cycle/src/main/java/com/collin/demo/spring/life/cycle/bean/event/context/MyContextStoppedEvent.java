package com.collin.demo.spring.life.cycle.bean.event.context;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyContextStoppedEvent implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        log.error("----> ContextStoppedEvent");
        PrintUtil.add("ContextStoppedEvent");
    }

}