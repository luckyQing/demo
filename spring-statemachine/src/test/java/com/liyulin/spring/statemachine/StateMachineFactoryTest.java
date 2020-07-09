package com.liyulin.spring.statemachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liyulin.spring.statemachine.factory.service.OrderServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateMachineFactoryTest extends TestCase {

    @Autowired
    private OrderServiceImpl factoryOrderService;

    @Test
    public void testMultThread() throws Exception {
    	int id=1;
        factoryOrderService.creat(id);

        factoryOrderService.pay(id);
        factoryOrderService.deliver(id);
        factoryOrderService.receive(id);
    }

    @Test
    public void testRetry() throws Exception {
    	int id=2;
        factoryOrderService.creat(id);

        factoryOrderService.pay(id);
        factoryOrderService.retry(id);

        factoryOrderService.deliver(id);
        factoryOrderService.retry(id);
    }
    
}