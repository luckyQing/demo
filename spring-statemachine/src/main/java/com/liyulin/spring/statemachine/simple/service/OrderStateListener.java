package com.liyulin.spring.statemachine.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Service;

import com.liyulin.spring.statemachine.simple.enums.OrderStatus;
import com.liyulin.spring.statemachine.simple.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.simple.vo.OrderVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 状态转换
 */
@Service("simpleOrderStateListener")
@WithStateMachine
@Slf4j
public class OrderStateListener {
	
	@Autowired
	private OrderServiceImpl orderService;

	@OnTransition(source = OrderStatus.State.WAIT_PAYMENT, target = OrderStatus.State.WAIT_DELIVER)
	public boolean payTransition(Message<OrderStatusChangeEvents> message) {
		OrderVO order = (OrderVO) message.getHeaders().get("order");
		orderService.getOrders().get(order.getId()).setStatus(OrderStatus.SIMPLE_WAIT_DELIVER);
		log.info("支付 headers={}", message.getHeaders().toString());
		return true;
	}

	@OnTransition(source = OrderStatus.State.WAIT_DELIVER, target = OrderStatus.State.WAIT_RECEIVE)
	public boolean deliverTransition(Message<OrderStatusChangeEvents> message) {
		OrderVO order = (OrderVO) message.getHeaders().get("order");
		orderService.getOrders().get(order.getId()).setStatus(OrderStatus.SIMPLE_WAIT_RECEIVE);
		log.info("发货 headers={}", message.getHeaders().toString());
		return true;
	}

	@OnTransition(source = OrderStatus.State.WAIT_RECEIVE, target = OrderStatus.State.FINISH)
	public boolean receiveTransition(Message<OrderStatusChangeEvents> message) {
		OrderVO order = (OrderVO) message.getHeaders().get("order");
		orderService.getOrders().get(order.getId()).setStatus(OrderStatus.SIMPLE_FINISH);
		log.info("收货 headers={}", message.getHeaders().toString());
		return true;
	}

}