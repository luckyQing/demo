package com.liyulin.rabbitmq.mq.producer;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.rabbitmq.consts.MqConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeadLetterMQProducerService {

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * 发送延迟消息
	 * 
	 * @param message
	 */
	public void send(String message) {
		log.info("send delay msg:" + message);
		amqpTemplate.convertAndSend(MqConstants.DeadLetter.EXCHANGE, MqConstants.DeadLetter.ROUTING, message,
				messagePostProcessor -> {
					messagePostProcessor.getMessageProperties()
							.setExpiration(String.valueOf(TimeUnit.SECONDS.toMillis(5)));
					return messagePostProcessor;
				});
	}

}