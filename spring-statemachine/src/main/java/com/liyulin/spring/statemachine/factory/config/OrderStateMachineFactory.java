package com.liyulin.spring.statemachine.factory.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import com.liyulin.spring.statemachine.factory.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.factory.vo.OrderVO;

@EnableStateMachineFactory
public class OrderStateMachineFactory extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvents> states) throws Exception {
        states.withStates().initial(OrderStatus.FACTORY_WAIT_PAYMENT).states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvents> transitions)
            throws Exception {
        transitions.withExternal()
                .source(OrderStatus.FACTORY_WAIT_PAYMENT).target(OrderStatus.FACTORY_WAIT_DELIVER).event(OrderStatusChangeEvents.PAYED)
                .and() .withExternal().source(OrderStatus.FACTORY_WAIT_DELIVER).target(OrderStatus.FACTORY_WAIT_RECEIVE).event(OrderStatusChangeEvents.DELIVERY)
                .and().withExternal().source(OrderStatus.FACTORY_WAIT_RECEIVE).target(OrderStatus.FACTORY_FINISH).event(OrderStatusChangeEvents.RECEIVED)
                
                .and().withInternal().source(OrderStatus.FACTORY_WAIT_PAYMENT).event(OrderStatusChangeEvents.RETRY)
                .and().withInternal().source(OrderStatus.FACTORY_WAIT_DELIVER).event(OrderStatusChangeEvents.RETRY)
                .and().withInternal().source(OrderStatus.FACTORY_WAIT_RECEIVE).event(OrderStatusChangeEvents.RETRY)
                .and().withExit();
    }

    /**
     * 持久化配置 实际使用中，可以配合redis等，进行持久化操作
     *
     * @return
     */
    @Bean
    public StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> orderStateMachineFactoryPersister() {
        return new DefaultStateMachinePersister<>(
                new StateMachinePersist<OrderStatus, OrderStatusChangeEvents, OrderVO>() {
                    @Override
                    public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvents> context, OrderVO order)
                            throws Exception {
                    	order.setStatus(context.getState());
                    }

                    @Override
                    public StateMachineContext<OrderStatus, OrderStatusChangeEvents> read(OrderVO order)
                            throws Exception {
                    	return new DefaultStateMachineContext<>(order.getStatus(), null, null, null, null, "orderStateMachineId");
                    }
                });
    }

}