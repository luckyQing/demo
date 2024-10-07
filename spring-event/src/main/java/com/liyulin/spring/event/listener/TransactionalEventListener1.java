package com.liyulin.spring.event.listener;

import com.liyulin.spring.event.dto.SyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class TransactionalEventListener1 {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, value = SyncEvent.class)
    public void message(SyncEvent event) {
        log.info("sync event {}", getClass().getSimpleName());
    }

}
