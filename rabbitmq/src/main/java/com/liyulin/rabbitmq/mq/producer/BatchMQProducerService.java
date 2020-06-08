package com.liyulin.rabbitmq.mq.producer;

import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.rabbitmq.consts.MqConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatchMQProducerService {

	@Autowired
	private BatchingRabbitTemplate batchingRabbitTemplate;

	/**
	 * 发送批量消息
	 * 
	 * @param message
	 */
	public void send(String message) {
		log.info("send msg{}", message);
		for (int i = 0; i < 1024; i++) {
			batchingRabbitTemplate.convertAndSend(MqConstants.Batch.EXCHANGE, MqConstants.Batch.ROUTING, message + i);
		}
	}

}