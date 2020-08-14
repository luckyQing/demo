package com.liyulin.spring.statemachine;

import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import com.liyulin.spring.statemachine.factory.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.factory.service.DbCache;
import com.liyulin.spring.statemachine.factory.service.OrderServiceImpl;
import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StateMachineFactoryTest extends TestCase {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void testOrderShip() throws Exception {
        int id = 1;
        orderService.creat(id);
        orderService.sendCommond(id, OrderStatusChangeEvents.PAYED);
        Assertions.assertThat(DbCache.getOrderVO(id).getStatus()).isEqualTo(OrderStatus.FACTORY_FINISH);
    }

}