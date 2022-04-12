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
public class FanoutJsonMQConsumerService {

    @RabbitListener(queues = MqConstants.Fanoutson.QUEUE1)
    public void consumerFanout11(@Payload ProductDto productDto, @Headers Map<String, Object> headers) {
        String msgId = String.valueOf(headers.get("id"));
        log.info("fanout|receiver11|msgId={},msg={}", msgId, productDto);
    }

    @RabbitListener(queues = MqConstants.Fanoutson.QUEUE1)
    public void consumerFanout12(@Payload ProductDto productDto, @Headers Map<String, Object> headers) {
        String msgId = String.valueOf(headers.get("id"));
        log.info("fanout|receiver12|msgId={},msg={}", msgId, productDto);
    }

    @RabbitListener(queues = MqConstants.Fanoutson.QUEUE2)
    public void consumerFanout2(@Payload ProductDto productDto, @Headers Map<String, Object> headers) {
        String msgId = String.valueOf(headers.get("id"));
        log.info("fanout|receiver2|msgId={},msg={}", msgId, productDto);
    }
}