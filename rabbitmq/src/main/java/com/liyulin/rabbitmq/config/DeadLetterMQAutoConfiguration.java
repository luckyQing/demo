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
 * 死信（延迟）消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
@ConditionalOnBean(RabbitMQCommonAutoConfiguration.class)
public class DeadLetterMQAutoConfiguration {

	@Bean
	public Queue deadLetterQueue() {
		Map<String, Object> args = new HashMap<>();
		// 声明死信交换机：x-dead-letter-exchange
		args.put("x-dead-letter-exchange", MqConstants.DeadLetter.EXCHANGE);
		// 声明死信路由键：x-dead-letter-routing-key
		args.put("x-dead-letter-routing-key", MqConstants.DeadLetter.REDIRECT_ROUTING);
		return QueueBuilder.durable(MqConstants.DeadLetter.QUEUE).withArguments(args).build();
	}

	@Bean
	public Exchange deadLetterExchange() {
		return ExchangeBuilder.directExchange(MqConstants.DeadLetter.EXCHANGE).durable(true).build();
	}

	@Bean
	public Binding deadLetterBinding() {
		return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(MqConstants.DeadLetter.ROUTING).noargs();
	}
	
	
	/**
     * 定义死信队列转发队列
     *
     * @return the queue
     */
    @Bean
    public Queue redirectQueue() {
        return QueueBuilder.durable(MqConstants.DeadLetter.REDIRECT_QUEUE).build();
    }
    
    /**
     * 死信路由通过 KEY_R 绑定键绑定到死信队列上
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
		return BindingBuilder.bind(redirectQueue()).to(deadLetterExchange()).with(MqConstants.DeadLetter.REDIRECT_ROUTING).noargs();
    }
    
}