package com.liyulin.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liyulin.rabbitmq.consts.MqConstants;

/**
 * 普通消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class StandardMQAutoConfiguration {

	@Bean
	public Queue amqpQueue() {
		return new Queue(MqConstants.Standard.QUEUE, true);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(MqConstants.Standard.EXCHANGE);
	}

	@Bean
	public Binding bindingDerect() {
		return BindingBuilder.bind(amqpQueue()).to(directExchange()).with(MqConstants.Standard.ROUTING);
	}

}