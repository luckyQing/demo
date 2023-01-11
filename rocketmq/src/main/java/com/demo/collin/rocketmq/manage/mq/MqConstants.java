package com.demo.collin.rocketmq.manage.mq;

public interface MqConstants {

    String CONSUMER_GROUP = "demo";

    interface Topic {
        String PREFIX = "demo_";
        String SYNCHRONOUSLY = PREFIX + "synchronously";
    }

    interface Tag {
        String SYNCHRONOUSLY = "synchronously";
    }

}