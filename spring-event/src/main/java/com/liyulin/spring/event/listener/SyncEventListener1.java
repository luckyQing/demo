package com.liyulin.spring.event.listener;

import com.liyulin.spring.event.dto.SyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(4)
public class SyncEventListener1 implements ApplicationListener<SyncEvent> {

    @Override
    public void onApplicationEvent(SyncEvent event) {
        log.info("sync event {}", getClass().getSimpleName());
    }

}
