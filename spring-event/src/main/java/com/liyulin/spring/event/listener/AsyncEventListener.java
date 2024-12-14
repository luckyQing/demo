package com.liyulin.spring.event.listener;

import com.liyulin.spring.event.dto.AsyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncEventListener implements ApplicationListener<AsyncEvent> {

    @Async
    @Override
    public void onApplicationEvent(AsyncEvent event) {
        log.info("async event {}", getClass().getSimpleName());
    }

}
