package com.liyulin.rabbitmq.mq.producer;

import java.util.concurrent.ExecutionException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.AsyncAmqpTemplate;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.liyulin.rabbitmq.consts.MqConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MqProducerService {

	@Autowired
	private AmqpTemplate amqpTemplate;
	@Autowired
	private BatchingRabbitTemplate batchingRabbitTemplate;
	@Autowired
	private AsyncAmqpTemplate asyncAmqpTemplate;

	/**
	 * 发送普通消息
	 * 
	 * @param message
	 */
	public void send(String message) {
		log.info("send msg:" + message);
		amqpTemplate.convertAndSend(MqConstants.Amqp.DirectExchange.TEST, MqConstants.Amqp.RoutingKey.TEST, message);
	}

	/**
	 * 异步发送消息
	 * 
	 * @param message
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public String asyncSend(String message) throws InterruptedException, ExecutionException {
		ListenableFuture<String> listenableFuture = asyncAmqpTemplate.convertSendAndReceive(
				MqConstants.AsyncAmqp.DirectExchange.TEST, MqConstants.AsyncAmqp.RoutingKey.TEST, message);
		
		String result = listenableFuture.get();
		log.info("result={}", result);
		return result;
	}

	/**
	 * 批量发送
	 * 
	 * @param message
	 * @return
	 */
	public Object batchSend(String message) {
		Object result = batchingRabbitTemplate.convertSendAndReceive(MqConstants.BatchAmqp.DirectExchange.TEST,
				MqConstants.BatchAmqp.RoutingKey.TEST, message);
		
		log.info("result={}", result);
		return result;
	}

}
