package com.liyulin.rabbitmq.mq.producer;

import com.liyulin.rabbitmq.consts.MqConstants;
import com.liyulin.rabbitmq.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StandardJsonMQProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送普通消息
     *
     * @param productDto
     */
    public void sendJson(ProductDto productDto) {
        log.info("send msg:" + productDto);
        rabbitTemplate.convertAndSend(MqConstants.StandardJson.EXCHANGE, MqConstants.StandardJson.ROUTING, productDto);
    }

}