package com.liyulin.redis.config;

import java.io.Serializable;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

/**
 * redis配置
 *
 * @author liyulin
 * @date 2018年10月17日下午10:58:24
 */
@Configuration
@EnableCaching
public class RedisConfig {

	@Bean
	public RedisTemplate<Serializable, Serializable> initRedisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setEnableDefaultSerializer(true);
		redisTemplate.setDefaultSerializer(new GenericFastJsonRedisSerializer());

		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public StringRedisTemplate initStringRedisTemplate(RedisConnectionFactory connectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(connectionFactory);
		stringRedisTemplate.setEnableDefaultSerializer(true);
		stringRedisTemplate.setDefaultSerializer(new GenericFastJsonRedisSerializer());

		stringRedisTemplate.afterPropertiesSet();
		return stringRedisTemplate;
	}

	@Bean
	public ValueOperations<Serializable, Serializable> redisValueOperations(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	@Bean
	public ListOperations<Serializable, Serializable> redisListOperations(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		return redisTemplate.opsForList();
	}

	@Bean
	public SetOperations<Serializable, Serializable> redisSetOperations(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	@Bean
	public ZSetOperations<Serializable, Serializable> redisZSetOperations(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		return redisTemplate.opsForZSet();
	}

	@Bean
	public HashOperations<Serializable, Serializable, Serializable> redisHashOperations(
			RedisTemplate<Serializable, Serializable> redisTemplate) {
		return redisTemplate.opsForHash();
	}

}
