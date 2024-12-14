package com.demo.collin.rocketmq.manage.mq.consumer;

import com.demo.collin.rocketmq.annotation.BatchConsumerConfig;
import com.demo.collin.rocketmq.dto.BatchConsumerDTO;
import com.demo.collin.rocketmq.manage.mq.AbstractRocketMQBatchListener;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@BatchConsumerConfig(consumeMessageBatchMaxSize = 10, pullBatchSize = 100)
@RocketMQMessageListener(consumerGroup = MqConstants.Group.BATCH_CONSUMER, topic = MqConstants.Topic.BATCH_CONSUMER)
public class BatchConsumer extends AbstractRocketMQBatchListener<BatchConsumerDTO> {

    @Override
    public void onMessage(List<BatchConsumerDTO> message) {
        log.info("msg={}", JacksonUtil.toJson(message));
    }

}