package com.liyulin.mocktest;

import java.lang.reflect.Field;

import org.springframework.test.util.ReflectionTestUtils;

import com.liyulin.mocktest.biz.AppLogBiz;

public class Test {

	public static void main(String[] args) {
		initMocks(new AppLogBiz(), null);
	}

	protected static <T, M> void initMocks(T targetObject, M mockObject) {
		Class<?> clazz = targetObject.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null) {
			return;
		}
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(mockObject.getClass())) {
				ReflectionTestUtils.setField(targetObject, field.getName(), mockObject);
			}
		}
	}
	
}
