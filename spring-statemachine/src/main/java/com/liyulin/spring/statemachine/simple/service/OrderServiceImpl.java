package com.liyulin.spring.statemachine.simple.service;

import com.liyulin.spring.statemachine.constants.SystemConstants;
import com.liyulin.spring.statemachine.simple.enums.OrderStatus;
import com.liyulin.spring.statemachine.simple.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.simple.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service("simpleOrderService")
@Slf4j
public class OrderServiceImpl {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvents> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> stateMachinePersister;

    public OrderVO creat(int id) {
        OrderVO order = new OrderVO();
        order.setId(id);
        order.setOrderNo("NO" + order.getId());
        order.setStatus(OrderStatus.SIMPLE_WAIT_PAYMENT);
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
     * @param order
     * @return
     */
    public boolean sendEvent(Message<OrderStatusChangeEvents> message) {
        boolean result = false;
        try {
            stateMachinePersister.restore(orderStateMachine, DbCache.getOrderVO(Integer.valueOf(message.getHeaders().get(SystemConstants.HEADER_KEY).toString())));
            result = orderStateMachine.sendEvent(message);
        } catch (Exception e) {
            log.error("send event exception", e);
        }
        return result;
    }

}