package com.liyulin.rabbitmq;

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
	public void testSend() {
		mqProducerService.send("hi");
	}
	
	@Test
	public void testBatchSend() {
		mqProducerService.send("hi");
	}
	
}
