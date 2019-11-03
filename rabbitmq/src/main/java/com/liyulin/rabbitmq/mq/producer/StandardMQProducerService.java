package com.liyulin.rabbitmq.mq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.rabbitmq.consts.MqConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StandardMQProducerService {

	@Autowired
	private AmqpTemplate amqpTemplate;

	/**
	 * 发送普通消息
	 * 
	 * @param message
	 */
	public void send(String message) {
		log.info("send msg:" + message);
		amqpTemplate.convertAndSend(MqConstants.Standard.EXCHANGE, MqConstants.Standard.ROUTING, message);
	}

}