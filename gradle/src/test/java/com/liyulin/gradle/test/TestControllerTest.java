package com.liyulin.gradle.test;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.alibaba.fastjson.TypeReference;
import com.liyulin.gradle.pojo.vo.TestResp;

public class TestControllerTest extends AbstractIntegrationTest {

	@Test
	public void testRest() throws Exception {
		TestResp testResp = super.get("/test", null, new TypeReference<TestResp>() {
		});
		Assertions.assertThat(testResp).isNotNull();
	}

}