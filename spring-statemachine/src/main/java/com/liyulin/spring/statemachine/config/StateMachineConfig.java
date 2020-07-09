package com.liyulin.spring.statemachine.config;

import java.util.EnumSet;

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

import com.liyulin.spring.statemachine.enums.OrderStatus;
import com.liyulin.spring.statemachine.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.vo.OrderVO;

@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvents> states) throws Exception {
        states.withStates().initial(OrderStatus.WAIT_PAYMENT).states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvents> transitions)
            throws Exception {
        transitions.withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvents.PAYED)
                .and() .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvents.DELIVERY)
                .and().withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvents.RECEIVED)
                
                .and().withInternal().source(OrderStatus.WAIT_PAYMENT).event(OrderStatusChangeEvents.RETRY)
                .and().withInternal().source(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvents.RETRY)
                .and().withInternal().source(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvents.RETRY)
                .and().withExit();
    }

    /**
     * 持久化配置 实际使用中，可以配合redis等，进行持久化操作
     *
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> persister() {
        return new DefaultStateMachinePersister<>(
                new StateMachinePersist<OrderStatus, OrderStatusChangeEvents, OrderVO>() {
                    @Override
                    public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvents> context, OrderVO order)
                            throws Exception {
                    }

                    @Override
                    public StateMachineContext<OrderStatus, OrderStatusChangeEvents> read(OrderVO order)
                            throws Exception {
                    	return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
                    }
                });
    }

}