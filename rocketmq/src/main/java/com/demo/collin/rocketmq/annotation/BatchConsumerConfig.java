package com.demo.collin.rocketmq.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BatchConsumerConfig {

    /**
     * 默认消费者单次批量消费的消息数目上限
     */
    int DEFAULT_CONSUME_MESSAGE_BATCH_MAX_SIZE = 8;
    /**
     * 默认每个队列每次拉取的最大消费数
     */
    int DEFAULT_PULL_BATCH_SIZE = 32;
    /**
     * 默认每次消息拉取的时间间隔（单位：毫秒）
     */
    long DEFAULT_PULL_INTERVAL = 1000L;

    /**
     * 默认最小消费线程池数
     */
    int DEFAULT_CONSUME_THREAD_MIN = 1;

    /**
     * 默认最大消费线程池数
     */
    int DEFAULT_CONSUME_THREAD_MAX = 4;

    /**
     * Message consume retry strategy<br>
     * -1,no retry,put into DLQ directly<br>
     * 0,broker control retry frequency<br>
     * >0,client control retry frequency
     */
    int DEFAULT_DELAY_LEVEL_WHEN_NEXT_CONSUME = 0;


    /**
     * 消费者单次批量消费的消息数目上限
     */
    int consumeMessageBatchMaxSize() default DEFAULT_CONSUME_MESSAGE_BATCH_MAX_SIZE;

    /**
     * 每个队列每次拉取的最大消费数
     *
     * @return
     */
    int pullBatchSize() default DEFAULT_PULL_BATCH_SIZE;

    /**
     * 每次消息拉取的时间间隔（单位：毫秒）
     *
     * @return
     */
    long pullInterval() default DEFAULT_PULL_INTERVAL;

    /**
     * 最小消费线程池数
     *
     * @return
     */
    int consumeThreadMin() default DEFAULT_CONSUME_THREAD_MIN;

    /**
     * 最大消费线程池数
     *
     * @return
     */
    int consumeThreadMax() default DEFAULT_CONSUME_THREAD_MAX;

    /**
     * Message consume retry strategy<br>
     * -1,no retry,put into DLQ directly<br>
     * 0,broker control retry frequency<br>
     * >0,client control retry frequency
     */
    int delayLevelWhenNextConsume() default DEFAULT_DELAY_LEVEL_WHEN_NEXT_CONSUME;

}