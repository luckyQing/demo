package com.liyulin.rabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liyulin.rabbitmq.consts.MqConstants;

/**
 * 延迟消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
@ConditionalOnBean(RabbitMQCommonAutoConfiguration.class)
public class DelayMQAutoConfiguration {

	@Bean
	public Queue delayQueue() {
		Map<String, Object> args = new HashMap<>(2);
//      x-dead-letter-exchange    声明  死信交换机
		args.put("x-dead-letter-exchange", "DL_EXCHANGE");
//      x-dead-letter-routing-key    声明 死信路由键
		args.put("x-dead-letter-routing-key", "KEY_R");
		return QueueBuilder.durable(MqConstants.Delay.QUEUE).withArguments(args).build();
	}

	@Bean
	public Exchange delayExchange() {
		return ExchangeBuilder.directExchange(MqConstants.Delay.EXCHANGE).durable(true).build();
	}

	@Bean
	public Binding bindingDelay() {
		return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(MqConstants.Delay.ROUTING).noargs();
	}
}