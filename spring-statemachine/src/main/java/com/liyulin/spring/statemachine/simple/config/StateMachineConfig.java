package com.liyulin.spring.statemachine.simple.config;

import com.liyulin.spring.statemachine.factory.service.OrderStateListener;
import com.liyulin.spring.statemachine.simple.enums.OrderStatus;
import com.liyulin.spring.statemachine.simple.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.simple.vo.OrderVO;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvents> {
    private ConcurrentMap<Integer, OrderVO> stateCache = new ConcurrentHashMap<>();

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvents> states) throws Exception {
        states.withStates().initial(OrderStatus.SIMPLE_WAIT_PAYMENT).end(OrderStatus.SIMPLE_FINISH).states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvents> transitions)
            throws Exception {
        transitions.withExternal()
                .source(OrderStatus.SIMPLE_WAIT_PAYMENT).target(OrderStatus.SIMPLE_WAIT_DELIVER).event(OrderStatusChangeEvents.SIMPLE_PAYED)
                .and().withExternal().source(OrderStatus.SIMPLE_WAIT_DELIVER).target(OrderStatus.SIMPLE_WAIT_RECEIVE).event(OrderStatusChangeEvents.SIMPLE_DELIVERY)
                .and().withExternal().source(OrderStatus.SIMPLE_WAIT_RECEIVE).target(OrderStatus.SIMPLE_FINISH).event(OrderStatusChangeEvents.SIMPLE_RECEIVED)

                .and().withInternal().source(OrderStatus.SIMPLE_WAIT_PAYMENT).event(OrderStatusChangeEvents.RETRY_PAYED)
                .and().withInternal().source(OrderStatus.SIMPLE_WAIT_DELIVER).event(OrderStatusChangeEvents.RETRY_DELIVERY)
                .and().withInternal().source(OrderStatus.SIMPLE_WAIT_RECEIVE).event(OrderStatusChangeEvents.RETRY_RECEIVED)
                .and().withInternal().source(OrderStatus.SIMPLE_FINISH).event(OrderStatusChangeEvents.SIMPLE_FINISH)
                .and().withExit();
    }

    /**
     * 持久化配置 实际使用中，可以配合redis等，进行持久化操作
     *
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> stateMachinePersister() {
        return new DefaultStateMachinePersister<>(
                new StateMachinePersist<OrderStatus, OrderStatusChangeEvents, OrderVO>() {
                    @Override
                    public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvents> context, OrderVO order)
                            throws Exception {
                        stateCache.put(order.getId(), order);
                    }

                    @Override
                    public StateMachineContext<OrderStatus, OrderStatusChangeEvents> read(OrderVO order)
                            throws Exception {
                        return stateCache.containsKey(order.getId()) ?
                                new DefaultStateMachineContext<>(stateCache.get(order.getId()).getStatus(), null, null, null, null, OrderStateListener.STATE_MACHINE_ID) :
                                new DefaultStateMachineContext<>(OrderStatus.SIMPLE_WAIT_PAYMENT, null, null, null, null, OrderStateListener.STATE_MACHINE_ID);
                    }
                });
    }

}