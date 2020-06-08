package com.liyulin.rabbitmq.mq.consumer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.BatchMessageListener;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.liyulin.rabbitmq.consts.MqConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BatchMQConsumerService implements BatchMessageListener {
	static AtomicInteger count = new AtomicInteger(0);

	@RabbitListener(queues = MqConstants.Batch.QUEUE, containerFactory="simpleRabbitListenerContainerFactory")
	@Override
	public void onMessageBatch(List<Message> messages) {
		log.info("receiver msg({}次->{})", count.incrementAndGet(), messages.size());
	}

}