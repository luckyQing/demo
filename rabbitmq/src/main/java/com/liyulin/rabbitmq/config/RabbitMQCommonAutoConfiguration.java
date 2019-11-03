package com.liyulin.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.AbstractConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * rabbitmq通用配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitMQCommonAutoConfiguration {

	@Autowired
	private RabbitProperties rabbitProperties;

	@Bean
	public ConnectionFactory cachingConnectionFactory() {
		AbstractConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(rabbitProperties.getHost());
		connectionFactory.setPort(rabbitProperties.getPort());
		connectionFactory.setUsername(rabbitProperties.getUsername());
		connectionFactory.setPassword(rabbitProperties.getPassword());
		connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());

		return connectionFactory;
	}

	@Bean
	public RetryTemplate retryTemplate() {
		// 设置connect失败之后的策略
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(500);
		backOffPolicy.setMaxInterval(100000);

		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setBackOffPolicy(backOffPolicy);
		return retryTemplate;
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, final RetryTemplate retryTemplate) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(connectionFactory);
		rabbitTemplate.setRetryTemplate(retryTemplate);
		rabbitTemplate.setReceiveTimeout(5000);
		return rabbitTemplate;
	}

}