package com.liyulin.spring.statemachine.factory.guards;

import com.liyulin.spring.statemachine.factory.enums.OrderStatus;
import com.liyulin.spring.statemachine.factory.enums.OrderStatusChangeEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

/**
 * @author collin
 * @date 2020-08-13
 */
@Component
@Slf4j
public class CommonCheckGurad implements Guard<OrderStatus, OrderStatusChangeEvents> {

    @Override
    public boolean evaluate(StateContext<OrderStatus, OrderStatusChangeEvents> context) {
        Exception e = context.getException();
        boolean success = (context.getException() == null);
        if (!success) {
            log.error("statemachine.error", context.getException());
        }
        return success;
    }

}