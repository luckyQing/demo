package com.liyulin.rabbitmq.config;

import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * rabbitmq通用配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class RabbitMQCommonAutoConfiguration {

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

	@Bean
	public BatchingRabbitTemplate batchingRabbitTemplate(final ConnectionFactory connectionFactory,
			final RetryTemplate retryTemplate) {
		SimpleBatchingStrategy batchingStrategy = new SimpleBatchingStrategy(128, 128, 64);

		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() << 1);
		threadPoolTaskScheduler.initialize();

		BatchingRabbitTemplate rabbitTemplate = new BatchingRabbitTemplate(connectionFactory, batchingStrategy,
				threadPoolTaskScheduler);
		rabbitTemplate.setRetryTemplate(retryTemplate);
		rabbitTemplate.setReceiveTimeout(5000);
		return rabbitTemplate;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
			final ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		// 设置批量
		factory.setBatchListener(true);
		factory.setConsumerBatchEnabled(true);
		factory.setBatchSize(16);

		return factory;
	}

}