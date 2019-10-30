package com.liyulin.rabbitmq;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.rabbitmq.mq.producer.MqProducerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MqTest {

	@Autowired
	private MqProducerService mqProducerService;
	
	@Test
	public void testSend() throws InterruptedException {
		mqProducerService.send("hi");
		TimeUnit.SECONDS.sleep(1);
	}
	
	@Test
	public void testBatchSend() throws InterruptedException {
		mqProducerService.send("hi");
		TimeUnit.SECONDS.sleep(1);
	}
	
}
