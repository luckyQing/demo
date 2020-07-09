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
    public void testMultThread() throws Exception {
    	int id=1;
        orderService.creat(id);

        orderService.pay(id);
        orderService.deliver(id);
        orderService.receive(id);
    }

    @Test
    public void testRetry() throws Exception {
    	int id=2;
        orderService.creat(id);

        orderService.pay(id);
        orderService.retry(id);

        orderService.deliver(id);
        orderService.retry(id);
    }
    
}