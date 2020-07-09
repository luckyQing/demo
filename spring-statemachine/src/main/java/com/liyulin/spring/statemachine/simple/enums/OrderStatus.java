package com.liyulin.spring.statemachine.simple.enums;

/**
 * 订单状态
 */
public enum OrderStatus {

	/** 待支付 */
	SIMPLE_WAIT_PAYMENT,
	/** 待发货 */
	SIMPLE_WAIT_DELIVER,
	/** 待收货 */
	SIMPLE_WAIT_RECEIVE,
	/** 订单结束 */
	SIMPLE_FINISH;

	public static interface State {
		/** 待支付 */
		String WAIT_PAYMENT = "SIMPLE_WAIT_PAYMENT";
		/** 待发货 */
		String WAIT_DELIVER = "SIMPLE_WAIT_DELIVER";
		/** 待收货 */
		String WAIT_RECEIVE = "SIMPLE_WAIT_RECEIVE";
		/** 订单结束 */
		String FINISH = "SIMPLE_FINISH";
	}

}