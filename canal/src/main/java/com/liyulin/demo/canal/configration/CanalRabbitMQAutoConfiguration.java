package com.liyulin.demo.canal.configration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 普通消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class CanalRabbitMQAutoConfiguration {

	@Bean
	public Queue amqpQueue() {
		return new Queue("canal-mysql");
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("canal-mysql");
	}

	@Bean
	public Binding bindingDerect() {
		return BindingBuilder.bind(amqpQueue()).to(directExchange()).with("mysql-binlog");
	}

}