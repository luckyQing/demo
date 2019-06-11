package com.liyulin.mocktest;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * SpringBoot单元测试基类
 *
 * @author liyulin
 * @date 2019年4月22日上午12:25:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public abstract class AbstractUnitTest {

	@Autowired
	protected WebApplicationContext applicationContext;
	protected static MockMvc mockMvc = null;
	
	@Before
	public void initMock() {
		if (mockMvc == null) {
			mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
		}
	}
	
	protected <T> T postJson(String url, Object req, TypeReference<T> typeReference) throws Exception {
		String requestBody = JSON.toJSONString(req);
		log.info("test.requestBody={}", requestBody);
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(requestBody)
					.accept(MediaType.APPLICATION_JSON_UTF8)
				).andReturn();

		String content = result.getResponse().getContentAsString();
		log.info("test.result={}", content);

		return JSON.parseObject(content, typeReference);
	}

	private static final boolean springAopPresent = ClassUtils.isPresent("org.springframework.aop.framework.Advised",
			ReflectionTestUtils.class.getClassLoader());
	private static ConcurrentLinkedQueue<MockDto> mockCache = new ConcurrentLinkedQueue<>();
	
	@Getter
	@Setter
	@AllArgsConstructor
	static class MockDto {
		Object targetObject;
		Class<?> mockClass;
	}
	
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	enum MockTypeEnum {
		MOCK_BEFORE(1), MOCK_AFTER(2);
		private int type;
	}
	
	@After
	public void after() {
		MockDto mockDto = null;
		while ((mockDto = mockCache.poll()) != null) {
			setMockAttribute(mockDto.getTargetObject(), applicationContext.getBean(mockDto.getMockClass()), MockTypeEnum.MOCK_AFTER);
		}
	}
	
	protected static void initMocks(Object targetObject, Object mockObject) {
		setMockAttribute(targetObject, mockObject, MockTypeEnum.MOCK_BEFORE);
	}
	
	protected static void setMockAttribute(Object targetObject, Object mockObject, MockTypeEnum mockTypeEnum) {
		if (targetObject != null && springAopPresent) {
			targetObject = AopTestUtils.getUltimateTargetObject(targetObject);
		}
		Field[] fields = targetObject.getClass().getDeclaredFields();
		if (fields == null) {
			return;
		}
		if (mockTypeEnum == MockTypeEnum.MOCK_BEFORE) {
			Class<?> mockClass = mockObject.getClass().getSuperclass();
			if (mockClass == Object.class) {
				mockClass = mockObject.getClass();
			}
			mockCache.add(new MockDto(targetObject, mockClass));
		}
		try {
			for (Field field : fields) {
				if (Object.class != field.getType() && field.getType().isInstance(mockObject)) {
					field.setAccessible(true);
					field.set(targetObject, mockObject);
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(e.getMessage(), e);
		}
	}
	
}