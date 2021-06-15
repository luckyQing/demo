package com.liyulin.rabbitmq.mq.consumer;

import com.liyulin.rabbitmq.consts.MqConstants;
import com.liyulin.rabbitmq.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StandardJsonMQConsumerService {

    @RabbitListener(queues = MqConstants.StandardJson.QUEUE)
    public void consumerJsonAmqp(@Payload ProductDto productDto, @Headers Map<String, Object> headers) {
        String msgId = String.valueOf(headers.get("id"));
        log.info("receiver|msgId={},msg={}", msgId, productDto);
    }

}