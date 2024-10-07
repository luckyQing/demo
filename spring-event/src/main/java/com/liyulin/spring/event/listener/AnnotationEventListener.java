package com.liyulin.spring.event.listener;

import com.liyulin.spring.event.dto.SyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnnotationEventListener {

    @EventListener(SyncEvent.class)
    public void message(SyncEvent event) {
        log.info("sync event {}", getClass().getSimpleName());
    }

}
