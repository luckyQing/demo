package com.liyulin.flyway.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.flyway.demo.mapper.ApiLogBaseMapper;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest extends TestCase {
	
	@Autowired
	private ApiLogBaseMapper apiLogBaseMapper;
	
	@Test
	public void testFlyway() {
		apiLogBaseMapper.selectByPrimaryKey(1L);
	}

}
