package com.demo.collin.rocketmq.test;

import com.demo.collin.rocketmq.dto.SynchronouslyDTO;
import com.demo.collin.rocketmq.manage.mq.producer.RocketMQTemplateAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RocketmqTest {

    @Autowired
    private RocketMQTemplateAdapter rocketMQTemplateAdapter;

    @Test
    void testSendSynchronously() throws InterruptedException {
        SynchronouslyDTO synchronouslyDTO = new SynchronouslyDTO();
        synchronouslyDTO.setId(10L);
        synchronouslyDTO.setName("mobile10");

        rocketMQTemplateAdapter.sendSynchronously(synchronouslyDTO);

        TimeUnit.MINUTES.sleep(10);
    }

}