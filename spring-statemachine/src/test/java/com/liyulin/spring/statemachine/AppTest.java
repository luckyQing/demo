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

        orderService.pay(1);
        orderService.deliver(1);
        orderService.receive(1);

        System.out.println(orderService.getOrders());
    }

}