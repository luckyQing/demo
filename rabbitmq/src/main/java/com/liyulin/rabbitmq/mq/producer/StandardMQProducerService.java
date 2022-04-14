package com.liyulin.rabbitmq.mq.producer;

import com.liyulin.rabbitmq.consts.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StandardMQProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送普通消息
     *
     * @param message
     */
    public void send(String message) {
        log.info("send msg:{}", message);
        rabbitTemplate.convertAndSend(MqConstants.Standard.EXCHANGE, MqConstants.Standard.ROUTING, message);
    }

}