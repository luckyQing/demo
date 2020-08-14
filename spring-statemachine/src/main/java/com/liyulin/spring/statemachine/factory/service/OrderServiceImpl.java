package com.liyulin.spring.statemachine.factory.service;

import com.liyulin.spring.statemachine.constants.SystemConstants;
import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import com.liyulin.spring.statemachine.factory.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.factory.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service("factoryOrderService")
@Slf4j
public class OrderServiceImpl {

    @Autowired
    StateMachineFactory<OrderStatus, OrderStatusChangeEvents> stateMachineFactory;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> orderStateMachineFactoryPersister;

    public OrderVO creat(int id) {
        OrderVO order = new OrderVO();
        order.setId(id);
        order.setOrderNo("NO" + order.getId());
        order.setStatus(OrderStatus.FACTORY_WAIT_PAYMENT);
        DbCache.add(order);
        return order;
    }

    public OrderVO sendCommond(int id, OrderStatusChangeEvents event) throws Exception {
        OrderVO order = DbCache.getOrderVO(id);
        log.info("threadName=" + Thread.currentThread().getName() + " order=" + order);
        if (!sendEvent(MessageBuilder.withPayload(event).setHeader(SystemConstants.HEADER_KEY, id).build())) {
            throw new Exception("threadName=" + Thread.currentThread().getName() + "失败，状态异常 order=" + order);
        }
        return order;
    }

    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @return
     */
    public boolean sendEvent(Message<OrderStatusChangeEvents> message) {
        boolean result = false;
        StateMachine<OrderStatus, OrderStatusChangeEvents> orderStateMachine = stateMachineFactory.getStateMachine();
        try {
            orderStateMachineFactoryPersister.restore(orderStateMachine, DbCache.getOrderVO(Integer.valueOf(message.getHeaders().get(SystemConstants.HEADER_KEY).toString())));
            result = orderStateMachine.sendEvent(message);
        } catch (Exception e) {
            log.error("send event exception", e);
        }
        return result;
    }

}