package com.collin.demo.spring.life.cycle.bean.event;

import com.collin.demo.spring.life.cycle.util.PrintUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyWebServerInitializedEvent implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        log.error("----> WebServerInitializedEvent");
        PrintUtil.add("WebServerInitializedEvent");
    }

}