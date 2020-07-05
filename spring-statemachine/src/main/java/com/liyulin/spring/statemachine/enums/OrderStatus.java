package com.liyulin.spring.statemachine.enums;

/**
 * 订单状态
 */
public enum OrderStatus {

	/** 待支付 */
	WAIT_PAYMENT,
	/** 待发货 */
	WAIT_DELIVER,
	/** 待收货 */
	WAIT_RECEIVE,
	/** 订单结束 */
	FINISH;

	public static interface State {
		/** 待支付 */
		String WAIT_PAYMENT = "WAIT_PAYMENT";
		/** 待发货 */
		String WAIT_DELIVER = "WAIT_DELIVER";
		/** 待收货 */
		String WAIT_RECEIVE = "WAIT_RECEIVE";
		/** 订单结束 */
		String FINISH = "FINISH";
	}

}