package com.liyulin.gradle.test;


import java.util.Objects;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringBoot集成测试基类
 *
 * @author liyulin
 * @date 2019-04-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public abstract class AbstractIntegrationTest extends TestCase {

	@Autowired
	protected WebApplicationContext applicationContext;
	protected MockMvc mockMvc = null;
	
	static {
		// 单元测试环境下，关闭eureka
		System.setProperty("eureka.client.enabled", "false");
	}
	
	@Before
	public void initMock() {
		if (mockMvc == null) {
			mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		}
	}
	
	/**
	 * post请求
	 * 
	 * @param url           请求mapping的地址
	 * @param req           请求参数
	 * @param typeReference 返回对象类型
	 * @return
	 * @throws Exception
	 */
	protected <T> T get(String url, MultiValueMap<String, String> params, TypeReference<T> typeReference)
			throws Exception {
		String requestBody = JSON.toJSONString(params);
		log.info("test.requestBody={}", requestBody);

		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(url)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8);
		if (!Objects.isNull(params)) {
			mockHttpServletRequestBuilder = mockHttpServletRequestBuilder.params(params);
		}

		MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();

		String content = result.getResponse().getContentAsString();
		log.info("test.result={}", content);

		return JSON.parseObject(content, typeReference);
	}
	
	/**
	 * post请求
	 * 
	 * @param url           请求mapping的地址
	 * @param req           请求参数
	 * @param typeReference 返回对象类型
	 * @return
	 * @throws Exception
	 */
	protected <T> T post(String url, Object req, TypeReference<T> typeReference) throws Exception {
		String requestBody = JSON.toJSONString(req);
		log.info("test.requestBody={}", requestBody);

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON_UTF8)
					.content(requestBody)
				).andReturn();

		String content = result.getResponse().getContentAsString();
		log.info("test.result={}", content);

		return JSON.parseObject(content, typeReference);
	}
	
}