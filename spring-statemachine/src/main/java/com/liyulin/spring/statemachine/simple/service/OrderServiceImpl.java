package com.liyulin.spring.statemachine.simple.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import com.liyulin.spring.statemachine.simple.enums.OrderStatus;
import com.liyulin.spring.statemachine.simple.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.simple.vo.OrderVO;

import lombok.extern.slf4j.Slf4j;

@Service("simpleOrderService")
@Slf4j
public class OrderServiceImpl {

    @Autowired
	private StateMachine<OrderStatus, OrderStatusChangeEvents> orderStateMachine;

	@Autowired
	private StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> stateMachinePersister;

	private static Map<Integer, OrderVO> orders = new HashMap<>();

	public OrderVO creat(int id) {
		OrderVO order = new OrderVO();
		order.setId(id);
		order.setOrderNo("NO" + order.getId());
		order.setStatus(OrderStatus.SIMPLE_WAIT_PAYMENT);
		orders.put(order.getId(), order);
		return order;
	}

	public OrderVO pay(int id) throws Exception {
		OrderVO order = orders.get(id);
		log.info("threadName=" + Thread.currentThread().getName() + "尝试支付 id=" + id);
		Message<OrderStatusChangeEvents> message = MessageBuilder.withPayload(OrderStatusChangeEvents.PAYED)
				.setHeader("order", order).build();
		if (!sendEvent(message, order)) {
			throw new Exception("threadName=" + Thread.currentThread().getName() + "支付失败, 状态异常 id=" + id);
		}
		return orders.get(id);
	}

	public OrderVO deliver(int id) throws Exception {
		OrderVO order = orders.get(id);
		log.info("threadName=" + Thread.currentThread().getName() + "尝试发货 id=" + id);
		if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvents.DELIVERY).setHeader("order", order).build(),
				order)) {
			throw new Exception("threadName=" + Thread.currentThread().getName() + "发货失败，状态异常 id=" + id);
		}
		return orders.get(id);
	}

	public OrderVO receive(int id) throws Exception {
		OrderVO order = orders.get(id);
		log.info("threadName=" + Thread.currentThread().getName() + "尝试收货 id=" + id);
		if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvents.RECEIVED).setHeader("order", order).build(),
				order)) {
			throw new Exception("threadName=" + Thread.currentThread().getName() + "收货失败，状态异常 id=" + id);
		}
		return orders.get(id);
	}

	public OrderVO retry(int id) throws Exception {
		OrderVO order = orders.get(id);
		log.info("threadName=" + Thread.currentThread().getName() + "重试 id=" + order);
		Message<OrderStatusChangeEvents> message = MessageBuilder.withPayload(OrderStatusChangeEvents.RETRY)
				.setHeader("order", order).build();
		if (!sendEvent(message, order)) {
			throw new Exception("threadName=" + Thread.currentThread().getName() + "重试, 状态异常 id=" + order);
		}
		return orders.get(id);
	}

	public Map<Integer, OrderVO> getOrders() {
		return orders;
	}

	/**
	 * 发送订单状态转换事件
	 *
	 * @param message
	 * @param order
	 * @return
	 */
	private boolean sendEvent(Message<OrderStatusChangeEvents> message, OrderVO order) {
		boolean result = false;
		try {
			orderStateMachine.start();
			stateMachinePersister.restore(orderStateMachine, order);
			result = orderStateMachine.sendEvent(message);
			// 持久化状态机状态
			stateMachinePersister.persist(orderStateMachine, order);
		} catch (Exception e) {
			log.error("send event exception", e);
		} finally {
			orderStateMachine.stop();
		}
		return result;
	}

}