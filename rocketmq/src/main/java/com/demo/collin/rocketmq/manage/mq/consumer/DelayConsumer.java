package com.demo.collin.rocketmq.manage.mq.consumer;

import com.demo.collin.rocketmq.dto.DelayDTO;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import com.demo.collin.rocketmq.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = MqConstants.Group.DELAY, topic = MqConstants.Topic.DELAY)
public class DelayConsumer implements RocketMQListener<DelayDTO> {

    @Override
    public void onMessage(DelayDTO message) {
        log.info("msg={}", JacksonUtil.toJson(message));
    }

}