package com.liyulin.spring.statemachine.factory.enums;

/**
 * 订单状态
 */
public enum OrderStatus {

	/** 待支付 */
	FACTORY_WAIT_PAYMENT,
	/** 待发货 */
	FACTORY_WAIT_DELIVER,
	/** 待收货 */
	FACTORY_WAIT_RECEIVE,
	/** 订单结束 */
	FACTORY_FINISH;

	public static interface State {
		/** 待支付 */
		String WAIT_PAYMENT = "FACTORY_WAIT_PAYMENT";
		/** 待发货 */
		String WAIT_DELIVER = "FACTORY_WAIT_DELIVER";
		/** 待收货 */
		String WAIT_RECEIVE = "FACTORY_WAIT_RECEIVE";
		/** 订单结束 */
		String FINISH = "FACTORY_FINISH";
	}

}