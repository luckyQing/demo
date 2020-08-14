package com.liyulin.spring.statemachine;

import com.liyulin.spring.statemachine.simple.enums.OrderStatus;
import com.liyulin.spring.statemachine.simple.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.simple.service.DbCache;
import com.liyulin.spring.statemachine.simple.service.OrderServiceImpl;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateMachineSimpleTest extends TestCase {

    @Autowired
    private OrderServiceImpl simpleOrderService;

    @Test
    public void testOrderShip() throws Exception {
        int id = 2;
        simpleOrderService.creat(id);

        simpleOrderService.sendCommond(id, OrderStatusChangeEvents.SIMPLE_PAYED);
        Assertions.assertThat(DbCache.getOrderVO(id).getStatus()).isEqualTo(OrderStatus.SIMPLE_FINISH);
    }

}