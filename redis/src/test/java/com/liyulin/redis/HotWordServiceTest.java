package com.liyulin.redis;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.liyulin.redis.service.HotWordService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class HotWordServiceTest {

	@Autowired
	private HotWordService hotWordService;

	@Test
	public void testSave() {
		for (int i = 0; i < 15; i++) {
			hotWordService.save("hotWord" + i);
		}
		for (int i = 0; i < 15; i = i + 2) {
			hotWordService.save("hotWord" + i);
		}
	}
	
	@Test
	public void testQuery() {
		Set<String> hotWords = hotWordService.query();
		log.info("hotWords={}", JSON.toJSONString(hotWords));
	}

}