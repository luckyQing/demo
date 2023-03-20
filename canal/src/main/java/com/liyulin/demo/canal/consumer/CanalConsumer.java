package com.liyulin.demo.canal.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RabbitListener(queues = "canal-mysql")
public class CanalConsumer {

    @RabbitHandler
    public void sync(@Payload byte[] msg) {
        log.warn("json={}", new String(msg, StandardCharsets.UTF_8));
    }

}