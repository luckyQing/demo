package com.liyulin.rabbitmq.config;

import com.liyulin.rabbitmq.consts.MqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic消息配置
 *
 * @author liyulin
 * @date 2019-11-02
 */
@Configuration
public class TopicMQAutoConfiguration {

    @Bean
    public Queue topicQueue1() {
        return new Queue(MqConstants.Topic.QUEUE1, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(MqConstants.Topic.QUEUE2, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConstants.Topic.EXCHANGE);
    }

    @Bean
    public Binding bindingTopicQueue1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(MqConstants.Topic.REDIRECT_ROUTING1);
    }

    @Bean
    public Binding bindingTopicQueue2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(MqConstants.Topic.REDIRECT_ROUTING2);
    }

}