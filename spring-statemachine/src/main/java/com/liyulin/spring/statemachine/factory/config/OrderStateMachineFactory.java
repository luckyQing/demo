package com.liyulin.spring.statemachine.factory.config;

import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import com.liyulin.spring.statemachine.factory.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.factory.guards.CommonCheckGurad;
import com.liyulin.spring.statemachine.factory.service.OrderStateListener;
import com.liyulin.spring.statemachine.factory.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.EnumSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@EnableStateMachineFactory
public class OrderStateMachineFactory extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvents> {

    @Autowired
    private CommonCheckGurad commonCheckGurad;
    private ConcurrentMap<Integer, OrderVO> stateCache = new ConcurrentHashMap<>();

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvents> states) throws Exception {
        states.withStates().initial(OrderStatus.FACTORY_WAIT_PAYMENT).end(OrderStatus.FACTORY_FINISH).states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvents> transitions)
            throws Exception {
        transitions.withExternal().source(OrderStatus.FACTORY_WAIT_PAYMENT).target(OrderStatus.FACTORY_WAIT_DELIVER).event(OrderStatusChangeEvents.PAYED).guard(commonCheckGurad)
                .and().withExternal().source(OrderStatus.FACTORY_WAIT_DELIVER).target(OrderStatus.FACTORY_WAIT_RECEIVE).event(OrderStatusChangeEvents.DELIVERY).guard(commonCheckGurad)
                .and().withExternal().source(OrderStatus.FACTORY_WAIT_RECEIVE).target(OrderStatus.FACTORY_FINISH).event(OrderStatusChangeEvents.RECEIVED).guard(commonCheckGurad)

                .and().withInternal().source(OrderStatus.FACTORY_WAIT_PAYMENT).event(OrderStatusChangeEvents.RETRY_PAYED).guard(commonCheckGurad)
                .and().withInternal().source(OrderStatus.FACTORY_WAIT_DELIVER).event(OrderStatusChangeEvents.RETRY_DELIVERY).guard(commonCheckGurad)
                .and().withInternal().source(OrderStatus.FACTORY_WAIT_RECEIVE).event(OrderStatusChangeEvents.RETRY_RECEIVED).guard(commonCheckGurad)
                .and().withInternal().source(OrderStatus.FACTORY_FINISH).event(OrderStatusChangeEvents.FINISH).guard(commonCheckGurad)
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
                        stateCache.put(order.getId(), order);
                    }

                    @Override
                    public StateMachineContext<OrderStatus, OrderStatusChangeEvents> read(OrderVO order)
                            throws Exception {
                        return stateCache.containsKey(order.getId()) ?
                                new DefaultStateMachineContext<>(stateCache.get(order.getId()).getStatus(), null, null, null, null, OrderStateListener.STATE_MACHINE_ID) :
                                new DefaultStateMachineContext<>(OrderStatus.FACTORY_WAIT_PAYMENT, null, null, null, null, OrderStateListener.STATE_MACHINE_ID);
                    }
                });
    }

}