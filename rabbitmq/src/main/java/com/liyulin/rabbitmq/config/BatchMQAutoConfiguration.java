package com.liyulin.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liyulin.rabbitmq.consts.MqConstants;

/**
 * 批量mq配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class BatchMQAutoConfiguration {

	@Bean
	public Queue batchQueue() {
		return new Queue(MqConstants.Batch.QUEUE, true);
	}

	@Bean
	public DirectExchange batchDirectExchange() {
		return new DirectExchange(MqConstants.Batch.EXCHANGE);
	}

	@Bean
	public Binding bindingBatchDerect(final Queue batchQueue, final DirectExchange batchDirectExchange) {
		return BindingBuilder.bind(batchQueue).to(batchDirectExchange).with(MqConstants.Batch.ROUTING);
	}

}