package com.liyulin.rabbitmq;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.rabbitmq.mq.producer.DelayMQProducerService;
import com.liyulin.rabbitmq.mq.producer.StandardMQProducerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MQTest {

	@Autowired
	private StandardMQProducerService standardMQProducerService;
	@Autowired
	private DelayMQProducerService delayMQProducerService;
	
	@Test
	public void testStandardMQSend() throws InterruptedException {
		standardMQProducerService.send("hi");
		TimeUnit.SECONDS.sleep(1);
	}
	
	@Test
	public void testDelayMQSend() throws InterruptedException {
		delayMQProducerService.send("hi");
		TimeUnit.SECONDS.sleep(6);
	}
	
}