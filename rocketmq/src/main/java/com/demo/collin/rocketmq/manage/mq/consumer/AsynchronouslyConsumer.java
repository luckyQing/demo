package com.demo.collin.rocketmq.manage.mq.consumer;

import com.demo.collin.rocketmq.dto.SynchronouslyDTO;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = MqConstants.Group.ASYNCHRONOUSLY, topic = MqConstants.Topic.ASYNCHRONOUSLY)
public class AsynchronouslyConsumer implements RocketMQListener<SynchronouslyDTO> {

    @Override
    public void onMessage(SynchronouslyDTO message) {
        log.info("msg={}", JacksonUtil.toJson(message));
    }

}