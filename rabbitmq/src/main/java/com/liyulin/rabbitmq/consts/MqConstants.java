package com.liyulin.rabbitmq.consts;

public final class MqConstants {

	/** 普通消息 */
	public static final class Standard {
		public static final String PREFIX = "standard_";
		public static final String QUEUE = PREFIX + "_mq";
		public static final String EXCHANGE = PREFIX + "exchange";
		public static final String ROUTING = PREFIX + "routingKey";
	}
	
	/** 延迟消息 */
	public static final class Delay {
		public static final String PREFIX = "delay_";
		public static final String QUEUE = PREFIX + "_mq";
		public static final String EXCHANGE = PREFIX + "exchange";
		public static final String ROUTING = PREFIX + "routingKey";
	}

}