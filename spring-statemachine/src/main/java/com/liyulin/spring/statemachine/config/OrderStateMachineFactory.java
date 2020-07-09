package com.liyulin.spring.statemachine.config;

import com.liyulin.spring.statemachine.enums.OrderStatus;
import com.liyulin.spring.statemachine.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.kryo.KryoStateMachineSerialisationService;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.service.StateMachineSerialisationService;

import java.util.EnumSet;

@Configuration
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@EnableStateMachineFactory
public class OrderStateMachineFactory extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvents> {

    @Autowired
    private HashOperations<String, Integer, byte[]> hashOperations;

    private final StateMachineSerialisationService<OrderStatus, OrderStatusChangeEvents> serialisationService = new KryoStateMachineSerialisationService<>();
    private static final String STATE_MACHINE_CONTEXT_CACHE_PREFIX = "cache:smc";

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvents> states) throws Exception {
        states.withStates().initial(OrderStatus.WAIT_PAYMENT).states(EnumSet.allOf(OrderStatus.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvents> transitions)
            throws Exception {
        transitions.withExternal()
                .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvents.PAYED)
                .and()
                .withExternal().source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE).event(OrderStatusChangeEvents.DELIVERY)
                .and()
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvents.RECEIVED);
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
                        byte[] stateMachineContextBytes = serialisationService.serialiseStateMachineContext(context);
                        hashOperations.put(STATE_MACHINE_CONTEXT_CACHE_PREFIX, order.getId(), stateMachineContextBytes);
                    }

                    @Override
                    public StateMachineContext<OrderStatus, OrderStatusChangeEvents> read(OrderVO order)
                            throws Exception {
                        byte[] stateMachineContextBytes = hashOperations.get(STATE_MACHINE_CONTEXT_CACHE_PREFIX, order.getId());
                        if(stateMachineContextBytes==null){
                            return null;
                        }
                        return serialisationService.deserialiseStateMachineContext(stateMachineContextBytes);
                    }
                });
    }

}