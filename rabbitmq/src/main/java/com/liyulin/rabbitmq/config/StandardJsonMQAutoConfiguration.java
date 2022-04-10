package com.liyulin.rabbitmq.config;

import com.liyulin.rabbitmq.consts.MqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 普通json消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class StandardJsonMQAutoConfiguration {

    @Bean
    public Queue amqpJsonQueue1() {
        return new Queue(MqConstants.StandardJson.QUEUE1, true);
    }
    @Bean
    public Queue amqpJsonQueue2() {
        return new Queue(MqConstants.StandardJson.QUEUE2, true);
    }

    @Bean
    public DirectExchange directJsonExchange() {
        return new DirectExchange(MqConstants.StandardJson.EXCHANGE);
    }

    @Bean
    public Binding bindingAmqpJsonQueue1() {
        return BindingBuilder.bind(amqpJsonQueue1()).to(directJsonExchange()).with(MqConstants.StandardJson.ROUTING);
    }

    @Bean
    public Binding bindingAmqpJsonQueue2() {
        return BindingBuilder.bind(amqpJsonQueue2()).to(directJsonExchange()).with(MqConstants.StandardJson.ROUTING);
    }

}