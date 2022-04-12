package com.liyulin.rabbitmq;

import com.liyulin.rabbitmq.dto.ProductDto;
import com.liyulin.rabbitmq.mq.producer.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MQTest {

    @Autowired
    private StandardMQProducerService standardMQProducerService;
    @Autowired
    private StandardJsonMQProducerService standardJsonMQProducerService;
    @Autowired
    private DeadLetterMQProducerService delayMQProducerService;
    @Autowired
    private BatchMQProducerService batchMQProducerService;
    @Autowired
    private FanoutJsonMQProducerService fanoutJsonMQProducerService;

    @Test
    public void testStandardMQSend() throws InterruptedException {
        standardMQProducerService.send("hi");
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void testStandardJsonMQSend() throws InterruptedException {
        standardJsonMQProducerService.sendJson(new ProductDto().setId(1L).setName("huawei mobile").setPrice(100L));
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testDeadLetterMQSend() throws InterruptedException {
        delayMQProducerService.send("hi");
        TimeUnit.SECONDS.sleep(6);
    }

    @Test
    public void testBatchMQ() throws InterruptedException {
        batchMQProducerService.send("hi");
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testFanoutJsonMQSend() throws InterruptedException {
        fanoutJsonMQProducerService.sendJson(new ProductDto().setId(1L).setName("huawei mobile").setPrice(100L));
        TimeUnit.SECONDS.sleep(10);
    }
}