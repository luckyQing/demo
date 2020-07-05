package com.liyulin.spring.statemachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liyulin.spring.statemachine.service.OrderServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest extends TestCase {

	@Autowired
	private OrderServiceImpl orderService;

	@Test
	public void testMultThread() {
		orderService.creat();
		orderService.creat();

		orderService.pay(1);

		new Thread(() -> {
			orderService.deliver(1);
			orderService.receive(1);
		}).start();

		orderService.pay(2);
		orderService.deliver(2);
		orderService.receive(2);

		System.out.println(orderService.getOrders());
	}

}