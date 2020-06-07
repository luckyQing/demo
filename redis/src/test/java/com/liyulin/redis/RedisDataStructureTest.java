package com.liyulin.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RedisDataStructureTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testHash() {
		HashOperations<String, String, String> redisHash = stringRedisTemplate.opsForHash();
		String key = "user:1";
		redisHash.put(key, "name", "张三");
		redisHash.put(key, "age", "18");

		Assertions.assertThat("张三").isEqualTo(redisHash.get(key, "name"));
		Assertions.assertThat("18").isEqualTo(redisHash.get(key, "age"));
	}

	@Test
	public void testSet() {
		SetOperations<String, String> redisSet = stringRedisTemplate.opsForSet();
		redisSet.add("1", "value1", "value2", "value3", "value4", "value5", "value6");
		redisSet.add("1", "value7");
		redisSet.add("2", "value2");
		Set<String> set = redisSet.members("1");

		stringRedisTemplate.expire("1", 1, TimeUnit.MINUTES);

		Assertions.assertThat(redisSet.isMember("1", "value4")).isTrue();
		Assertions.assertThat(set.size()).isEqualTo(7);
	}

	@Test
	public void testList() {
		ListOperations<String, String> redisList = stringRedisTemplate.opsForList();
		for (int i = 0; i < 5; i++) {
			redisList.rightPush("product", "商品" + i);
		}
		
		for (int i = 0; i < 5; i++) {
			String value = redisList.leftPop("product");
			log.info(value);
		}
	}

}