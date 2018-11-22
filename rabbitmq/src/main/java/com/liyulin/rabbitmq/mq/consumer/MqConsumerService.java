package com.liyulin.rabbitmq.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.liyulin.rabbitmq.consts.MqConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MqConsumerService {

	@RabbitListener(queues = MqConstants.Amqp.RoutingKey.TEST)
	public void consumerAmqp(String msg) {
		log.info("receiver msg:" + msg);
	}

	@RabbitListener(queues = MqConstants.AsyncAmqp.RoutingKey.TEST)
	public void consumerAsyncAmqp(String msg) {
		log.info("receiver msg:" + msg);
	}

	@RabbitListener(queues = MqConstants.BatchAmqp.RoutingKey.TEST)
	public void consumerBatchAmqp(String msg) {
		log.info("receiver msg:" + msg);
	}
}
