package com.collin.demo.spring.life.cycle.bean.event;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Slf4j
@Component
public class MyRequestHandledEvent implements ApplicationListener<RequestHandledEvent> {

    @Override
    public void onApplicationEvent(RequestHandledEvent event) {
        log.error("----> RequestHandledEvent");
        PrintUtil.add("RequestHandledEvent");
    }

}