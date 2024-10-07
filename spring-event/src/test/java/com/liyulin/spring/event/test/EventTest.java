package com.liyulin.spring.event.test;

import com.liyulin.spring.event.service.EventPublishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private EventPublishService eventPublishService;

    @Test
    public void testSyncEvent() {
        eventPublishService.sendSyncEvent();
    }

}