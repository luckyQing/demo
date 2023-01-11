package com.demo.collin.rocketmq.manage.mq.producer;

import com.demo.collin.rocketmq.dto.SynchronouslyDTO;
import com.demo.collin.rocketmq.manage.mq.MqConstants;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RocketMQTemplateAdapter {

    private final RocketMQTemplate rocketMQTemplate;

    public void sendSynchronously(SynchronouslyDTO payload) {
        rocketMQTemplate.syncSend(MqConstants.Topic.SYNCHRONOUSLY, payload);
    }

}