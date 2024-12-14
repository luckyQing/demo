package com.liyulin.spring.event.test;

import com.liyulin.spring.event.dto.AsyncEvent;
import com.liyulin.spring.event.dto.SyncEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testSyncEvent() {
        applicationContext.publishEvent(new SyncEvent(this, "test sync event"));
    }

    @Test
    public void testAsyncEvent() throws InterruptedException {
        applicationContext.publishEvent(new AsyncEvent(this, "test async event"));
        TimeUnit.SECONDS.sleep(5);
    }

}