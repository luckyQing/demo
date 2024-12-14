package com.demo.collin.rocketmq.manage.mq;

public interface MqConstants {

    interface Group {
        String PREFIX = "demo_";
        String TAIL = "_group";
        /**
         * 同步
         */
        String SYNCHRONOUSLY = PREFIX + "synchronously" + TAIL;
        /**
         * 异步
         */
        String ASYNCHRONOUSLY = PREFIX + "asynchronously" + TAIL;
        /**
         * 延迟
         */
        String DELAY = PREFIX + "delay" + TAIL;
        /**
         * 批量发送
         */
        String BATCH_SEND = PREFIX + "batch_send" + TAIL;
        /**
         * 批量消费
         */
        String BATCH_CONSUMER = PREFIX + "batch_consumer" + TAIL;
    }

    interface Topic {
        String PREFIX = "demo_";
        /**
         * 同步
         */
        String SYNCHRONOUSLY = PREFIX + "synchronously";
        /**
         * 异步
         */
        String ASYNCHRONOUSLY = PREFIX + "asynchronously";
        /**
         * 延迟
         */
        String DELAY = PREFIX + "delay";
        /**
         * 批量发送
         */
        String BATCH_SEND = PREFIX + "batch_send";
        /**
         * 批量消费
         */
        String BATCH_CONSUMER = PREFIX + "batch_consumer";
    }

}