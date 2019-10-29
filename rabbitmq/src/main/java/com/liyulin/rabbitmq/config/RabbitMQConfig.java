package com.liyulin.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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

import com.liyulin.rabbitmq.consts.MqConstants;

@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitMQConfig {

	@Autowired
	private RabbitProperties rabbitProperties;

	@Bean
	public ConnectionFactory cachingConnectionFactory() {
		com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory = new com.rabbitmq.client.ConnectionFactory();
		rabbitConnectionFactory.setHost(rabbitProperties.getHost());
		rabbitConnectionFactory.setPort(rabbitProperties.getPort());
		rabbitConnectionFactory.setUsername(rabbitProperties.getUsername());
		rabbitConnectionFactory.setPassword(rabbitProperties.getPassword());
		rabbitConnectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());

		return new CachingConnectionFactory(rabbitConnectionFactory);
	}

	@Bean
	public Queue amqpQueue() {
		return new Queue(MqConstants.Amqp.RoutingKey.TEST, true);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(MqConstants.Amqp.DirectExchange.TEST);
	}

	@Bean
	public Binding bindingDerect() {
		return BindingBuilder.bind(amqpQueue()).to(directExchange()).with(MqConstants.Amqp.RoutingKey.TEST);
	}

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
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		rabbitTemplate.setConnectionFactory(cachingConnectionFactory());
		rabbitTemplate.setRetryTemplate(retryTemplate());
		rabbitTemplate.setReceiveTimeout(5000);
		return rabbitTemplate;
	}
	
	/*@Bean
	public BatchingRabbitTemplate batchingRabbitTemplate() {
		SimpleBatchingStrategy  batchingStrategy = new SimpleBatchingStrategy(128, 128, 10000) ;
		
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors()<<1);
		
		BatchingRabbitTemplate rabbitTemplate = new BatchingRabbitTemplate(batchingStrategy, threadPoolTaskScheduler);
		
		rabbitTemplate.setConnectionFactory(cachingConnectionFactory());
		rabbitTemplate.setRetryTemplate(retryTemplate());
		rabbitTemplate.setReceiveTimeout(5000);
		return rabbitTemplate;
	}*/
}
