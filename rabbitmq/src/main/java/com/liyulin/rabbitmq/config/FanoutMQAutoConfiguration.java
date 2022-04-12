package com.liyulin.rabbitmq.config;

import com.liyulin.rabbitmq.consts.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class FanoutMQAutoConfiguration {

	@Bean
	public Queue fanoutQueue1() {
		return new Queue(MqConstants.Fanoutson.QUEUE1, true);
	}
	@Bean
	public Queue fanoutQueue2() {
		return new Queue(MqConstants.Fanoutson.QUEUE2, true);
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(MqConstants.Fanoutson.EXCHANGE);
	}

	@Bean
	public Binding bindingFanoutQueue1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
	}

	@Bean
	public Binding bindingFanoutQueue2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
	}

}