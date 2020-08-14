package com.liyulin.spring.statemachine.factory.service;

import com.liyulin.spring.statemachine.constants.SystemConstants;
import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import com.liyulin.spring.statemachine.factory.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.factory.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * 状态转换
 */
@Service("factoryOrderStateListener")
@WithStateMachine(id = OrderStateListener.STATE_MACHINE_ID)
@Slf4j
public class OrderStateListener {

    public static final String STATE_MACHINE_ID = "orderStateMachineId";
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    StateMachineFactory<OrderStatus, OrderStatusChangeEvents> stateMachineFactory;
    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> orderStateMachineFactoryPersister;

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

    @OnTransition(source = OrderStatus.State.FINISH)
    public boolean finishTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        log.info("完成 headers={}", message.getHeaders().toString());
        context.getStateMachine().stop();
        return true;
    }

    private boolean executeOrderStateTransition(Message<OrderStatusChangeEvents> message, StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        try {
            OrderVO order = DbCache.getOrderVO(Integer.valueOf(message.getHeaders().get(SystemConstants.HEADER_KEY).toString()));
            log.info("headers=============>{}", order);
//            if (order.getStatus() == OrderStatus.FACTORY_WAIT_PAYMENT) {
//                throw new NullPointerException("重试测试");
//            } else {
            order.setStatus(getNextOrderStatus(order.getStatus()));
            // 持久化状态机状态
            orderStateMachineFactoryPersister.persist(context.getStateMachine(), order);
            orderService.sendEvent(MessageBuilder.withPayload(getNextOrderStatusChangeEvents(message.getPayload())).setHeader(SystemConstants.HEADER_KEY, order.getId()).build());
//            }
        } catch (Exception e) {
            context.getStateMachine().stop();
            log.error("state machine error", e);
        }
        return true;
    }

    private OrderStatus getNextOrderStatus(OrderStatus orderStatus) {
        if (orderStatus == OrderStatus.FACTORY_WAIT_PAYMENT) {
            return OrderStatus.FACTORY_WAIT_DELIVER;
        } else if (orderStatus == OrderStatus.FACTORY_WAIT_DELIVER) {
            return OrderStatus.FACTORY_WAIT_RECEIVE;
        } else if (orderStatus == OrderStatus.FACTORY_WAIT_RECEIVE) {
            return OrderStatus.FACTORY_FINISH;
        }
        return null;
    }

    private OrderStatusChangeEvents getNextOrderStatusChangeEvents(OrderStatusChangeEvents event) {
        if (event == OrderStatusChangeEvents.PAYED) {
            return OrderStatusChangeEvents.DELIVERY;
        } else if (event == OrderStatusChangeEvents.DELIVERY) {
            return OrderStatusChangeEvents.RECEIVED;
        } else if (event == OrderStatusChangeEvents.RECEIVED) {
            return OrderStatusChangeEvents.FINISH;
        }
        return null;
    }

}