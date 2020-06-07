package com.liyulin.redis;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.redis.service.RedisComponent;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {

	@Autowired
	private RedisComponent redisComponent;
	
	@Test
	public void testRedis() {
		String key = "test";
		String value1 = "444";
		redisComponent.setString(key, value1, 1000*1000L);
		String value2 = redisComponent.getString(key);

		Assertions.assertThat(value2).isEqualTo(value1);
	}
	
	@Test
	public void testRedisTransactional() {
		List<String> keys = new ArrayList<>();
		List<String> values = new ArrayList<>();
		for(int i=0; i<10; i++) {
			keys.add("keyx"+i);
			values.add("valuex"+i);
		}
		
		boolean result = redisComponent.batchSetString(keys, values, 1000L);
		Assertions.assertThat(result).isEqualTo(true);
	}

	@Test
	public void testSetNx() {
		boolean success = redisComponent.setNx("333", "222", 2000000);
		Assertions.assertThat(success).isEqualTo(true);
	}
	
}