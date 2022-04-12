package com.liyulin.rabbitmq.consts;

public final class MqConstants {

	/** 普通消息 */
	public static final class Standard {
		private static final String PREFIX = "standard_";
		public static final String QUEUE = PREFIX + "mq";
		public static final String EXCHANGE = PREFIX + "exchange";
		public static final String ROUTING = PREFIX + "routingKey";
	}

	/** 普通json消息 */
	public static final class StandardJson {
		private static final String PREFIX = "standard_json_";
		public static final String QUEUE1 = PREFIX + "mq1";
		public static final String QUEUE2 = PREFIX + "mq2";
		public static final String EXCHANGE = PREFIX + "exchange";
		public static final String ROUTING = PREFIX + "routingKey";
	}
	
	/** 延迟消息 */
	public static final class DeadLetter {
		private static final String PREFIX = "deadLetter_";
		public static final String QUEUE = PREFIX + "mq";
		public static final String EXCHANGE = PREFIX + "exchange";
		public static final String ROUTING = PREFIX + "routingKey";
		

		private static final String REDIRECT_PREFIX = "redirect_";
		public static final String REDIRECT_QUEUE = REDIRECT_PREFIX + "mq";
		public static final String REDIRECT_EXCHANGE = EXCHANGE;
		public static final String REDIRECT_ROUTING = REDIRECT_PREFIX + "routingKey";
	}	
	
	/** 批量消息 */
	public static final class Batch {
		private static final String PREFIX = "batch_";
		public static final String QUEUE = PREFIX + "mq";
		public static final String EXCHANGE = PREFIX + "exchange";
		public static final String ROUTING = PREFIX + "routingKey";
	}

	/** fanoutjson消息 */
	public static final class Fanoutson {
		private static final String PREFIX = "fanout_json_";
		public static final String QUEUE1 = PREFIX + "mq1";
		public static final String QUEUE2 = PREFIX + "mq2";
		public static final String EXCHANGE = PREFIX + "exchange";
	}

}