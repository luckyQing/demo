package com.liyulin.spring.statemachine.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import com.liyulin.spring.statemachine.enums.OrderStatus;
import com.liyulin.spring.statemachine.enums.OrderStatusChangeEvents;
import com.liyulin.spring.statemachine.vo.OrderVO;

@Service
public class OrderServiceImpl {

	@Autowired
	private StateMachine<OrderStatus, OrderStatusChangeEvents> orderStateMachine;

	@Autowired
	private StateMachinePersister<OrderStatus, OrderStatusChangeEvents, OrderVO> persister;

	private int id = 1;
	private Map<Integer, OrderVO> orders = new HashMap<>();

	public OrderVO creat() {
		OrderVO order = new OrderVO();
		order.setStatus(OrderStatus.WAIT_PAYMENT);
		order.setId(id++);
		orders.put(order.getId(), order);
		return order;
	}

	public OrderVO pay(int id) {
		OrderVO order = orders.get(id);
		System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试支付 id=" + id);
		Message<OrderStatusChangeEvents> message = MessageBuilder.withPayload(OrderStatusChangeEvents.PAYED)
				.setHeader("order", order).build();
		if (!sendEvent(message, order)) {
			System.out.println("threadName=" + Thread.currentThread().getName() + " 支付失败, 状态异常 id=" + id);
		}
		return orders.get(id);
	}

	public OrderVO deliver(int id) {
		OrderVO order = orders.get(id);
		System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试发货 id=" + id);
		if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvents.DELIVERY).setHeader("order", order).build(),
				order)) {
			System.out.println("threadName=" + Thread.currentThread().getName() + " 发货失败，状态异常 id=" + id);
		}
		return orders.get(id);
	}

	public OrderVO receive(int id) {
		OrderVO order = orders.get(id);
		System.out.println("threadName=" + Thread.currentThread().getName() + " 尝试收货 id=" + id);
		if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvents.RECEIVED).setHeader("order", order).build(),
				order)) {
			System.out.println("threadName=" + Thread.currentThread().getName() + " 收货失败，状态异常 id=" + id);
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
	private synchronized boolean sendEvent(Message<OrderStatusChangeEvents> message, OrderVO order) {
		boolean result = false;
		try {
			orderStateMachine.start();
			// 尝试恢复状态机状态
			persister.restore(orderStateMachine, order);
			// 添加延迟用于线程安全测试
			Thread.sleep(1000);
			result = orderStateMachine.sendEvent(message);
			// 持久化状态机状态
			persister.persist(orderStateMachine, order);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			orderStateMachine.stop();
		}
		return result;
	}

}