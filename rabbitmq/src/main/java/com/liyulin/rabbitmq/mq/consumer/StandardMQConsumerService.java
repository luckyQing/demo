package com.liyulin.rabbitmq.mq.consumer;

import com.liyulin.rabbitmq.consts.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StandardMQConsumerService {

    @RabbitListener(queues = MqConstants.Standard.QUEUE)
    public void consumerAmqp(String msg) {
        log.info("receiver msg:{}", msg);
    }

}