package com.liyulin.rabbitmq.consts;

public final class MqConstants {

	/** 普通消息 */
	public static final class Amqp {
		private static final String PREFIX = "Amqp_";

		public static final class RoutingKey {
			public static final String TEST = PREFIX + "test_routingKey";
		}

		public static final class DirectExchange {
			public static final String TEST = PREFIX + "test_exchange";
		}

	}

	/** 异步消息 */
	public static final class AsyncAmqp {
		private static final String PREFIX = "AsyncAmqp_";

		public static final class RoutingKey {
			public static final String TEST = PREFIX + "test_routingKey";
		}

		public static final class DirectExchange {
			public static final String TEST = PREFIX + "test_exchange";
		}

	}

	/**批量发送消息*/
	public static final class BatchAmqp {
		private static final String PREFIX = "Batch_";

		public static final class RoutingKey {
			public static final String TEST = PREFIX + "test_routingKey";
		}

		public static final class DirectExchange {
			public static final String TEST = PREFIX + "test_exchange";
		}

	}
	
}
