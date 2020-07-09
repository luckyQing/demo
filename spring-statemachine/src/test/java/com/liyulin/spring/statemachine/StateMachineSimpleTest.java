package com.liyulin.spring.statemachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liyulin.spring.statemachine.simple.service.OrderServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateMachineSimpleTest extends TestCase {

    @Autowired
    private OrderServiceImpl simpleOrderService;

    @Test
    public void testMultThread() throws Exception {
    	int id=1;
        simpleOrderService.creat(id);

        simpleOrderService.pay(id);
        simpleOrderService.deliver(id);
        simpleOrderService.receive(id);
    }

    @Test
    public void testRetry() throws Exception {
    	int id=2;
        simpleOrderService.creat(id);

        simpleOrderService.pay(id);
        simpleOrderService.retry(id);

        simpleOrderService.deliver(id);
        simpleOrderService.retry(id);
    }
    
}