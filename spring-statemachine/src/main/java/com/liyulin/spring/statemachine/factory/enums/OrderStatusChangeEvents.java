package com.liyulin.spring.statemachine.factory.enums;

/**
 * 订单状态改变事件
 */
public enum OrderStatusChangeEvents {

	/** 支付 */
	PAYED,
	/** 发货 */
	DELIVERY,
	/** 确认收货 */
	RECEIVED,
	/** 重试 */
	RETRY;;

}