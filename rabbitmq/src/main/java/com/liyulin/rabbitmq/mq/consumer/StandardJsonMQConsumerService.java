package com.liyulin.rabbitmq.mq.consumer;

import com.liyulin.rabbitmq.consts.MqConstants;
import com.liyulin.rabbitmq.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StandardJsonMQConsumerService {

    @RabbitListener(queues = MqConstants.StandardJson.QUEUE1)
    public void consumerJsonAmqp1(@Payload ProductDto productDto, @Headers Map<String, Object> headers) {
        String msgId = String.valueOf(headers.get("id"));
        log.info("receiver1|msgId={},msg={}", msgId, productDto);
    }


    @RabbitListener(queues = MqConstants.StandardJson.QUEUE2)
    public void consumerJsonAmqp2(@Payload ProductDto productDto, @Headers Map<String, Object> headers) {
        String msgId = String.valueOf(headers.get("id"));
        log.info("receiver2|msgId={},msg={}", msgId, productDto);
    }
}