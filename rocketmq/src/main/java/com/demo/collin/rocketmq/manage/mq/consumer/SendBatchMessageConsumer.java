package com.demo.collin.rocketmq.manage.mq.consumer;

import com.demo.collin.rocketmq.dto.BatchSendDTO;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


/**
 * 消费批量发送的消息
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = MqConstants.Group.BATCH_SEND, topic = MqConstants.Topic.BATCH_SEND)
public class SendBatchMessageConsumer implements RocketMQListener<BatchSendDTO> {

    @Override
    public void onMessage(BatchSendDTO message) {
        log.info("BatchConsumer|msg={}", JacksonUtil.toJson(message));
    }

}