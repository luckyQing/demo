package com.liyulin.spring.statemachine.simple.service;

import com.liyulin.spring.statemachine.constants.SystemConstants;
import com.liyulin.spring.statemachine.simple.enums.OrderStatus;
import com.liyulin.spring.statemachine.simple.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.simple.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * 状态转换
 */
@Service("simpleOrderStateListener")
@WithStateMachine
@Slf4j
public class OrderStateListener {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> stateMachinePersister;

    @OnTransition(source = OrderStatus.State.WAIT_PAYMENT, target = {OrderStatus.State.WAIT_DELIVER, OrderStatus.State.WAIT_PAYMENT})
    public boolean payTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        return executeOrderStateTransition(message, context);
    }

    @OnTransition(source = OrderStatus.State.WAIT_DELIVER, target = {OrderStatus.State.WAIT_RECEIVE, OrderStatus.State.WAIT_DELIVER})
    public boolean deliverTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        return executeOrderStateTransition(message, context);
    }

    @OnTransition(source = OrderStatus.State.WAIT_RECEIVE, target = {OrderStatus.State.FINISH, OrderStatus.State.WAIT_RECEIVE})
    public boolean receiveTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        return executeOrderStateTransition(message, context);
    }

    @OnTransition(source = OrderStatus.State.WAIT_RECEIVE)
    public boolean finishTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        log.info("完成 headers={}", message.getHeaders().toString());
        context.getStateMachine().stop();
        return true;
    }

    private boolean executeOrderStateTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        try {
            OrderVO order = DbCache.getOrderVO(Integer.valueOf(message.getHeaders().get(SystemConstants.HEADER_KEY).toString()));
            log.info("headers=============>{}", order);
            order.setStatus(getNextOrderStatus(order.getStatus()));
            // 持久化状态机状态
            stateMachinePersister.persist(context.getStateMachine(), order);
            orderService.sendEvent(MessageBuilder.withPayload(getNextOrderStatusChangeEvents(message.getPayload())).setHeader(SystemConstants.HEADER_KEY, order.getId()).build());
        } catch (Exception e) {
            context.getStateMachine().stop();
            log.error("state machine error", e);
        }
        return true;
    }

    private OrderStatus getNextOrderStatus(OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.SIMPLE_WAIT_PAYMENT) {
            return OrderStatus.SIMPLE_WAIT_DELIVER;
        } else if (orderStatus == OrderStatus.SIMPLE_WAIT_DELIVER) {
            return OrderStatus.SIMPLE_WAIT_RECEIVE;
        } else if (orderStatus == OrderStatus.SIMPLE_WAIT_RECEIVE) {
            return OrderStatus.SIMPLE_FINISH;
        }
        return null;
    }

    private OrderStatusChangeEvents getNextOrderStatusChangeEvents(OrderStatusChangeEvents event) {
        if (event == OrderStatusChangeEvents.SIMPLE_PAYED) {
            return OrderStatusChangeEvents.SIMPLE_DELIVERY;
        } else if (event == OrderStatusChangeEvents.SIMPLE_DELIVERY) {
            return OrderStatusChangeEvents.SIMPLE_RECEIVED;
        } else if (event == OrderStatusChangeEvents.SIMPLE_RECEIVED) {
            return OrderStatusChangeEvents.SIMPLE_FINISH;
        }
        return null;
    }

}