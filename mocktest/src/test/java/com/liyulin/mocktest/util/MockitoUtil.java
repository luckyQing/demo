package com.liyulin.mocktest.util;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.mockito.internal.util.MockUtil;
import org.mockito.mock.MockCreationSettings;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ClassUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class MockitoUtil {

	private static final boolean springAopPresent = ClassUtils.isPresent("org.springframework.aop.framework.Advised",
			ReflectionTestUtils.class.getClassLoader());
	@Getter
	private static ConcurrentLinkedQueue<MockDto> mockCache = new ConcurrentLinkedQueue<>();

	public static void setMockAttribute(Object targetObject, Object mockObject, MockTypeEnum mockTypeEnum) {
		if (targetObject != null && springAopPresent) {
			targetObject = AopTestUtils.getUltimateTargetObject(targetObject);
		}
		Field[] fields = targetObject.getClass().getDeclaredFields();
		if (fields == null) {
			return;
		}
		if (mockTypeEnum == MockTypeEnum.MOCK_BEFORE) {
			MockCreationSettings<?> mockCreationSettings = MockUtil.getMockSettings(mockObject);
			Class<?> typeToMock = mockCreationSettings.getTypeToMock();
			mockCache.add(new MockDto(targetObject, typeToMock));
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

	@Getter
	@Setter
	@AllArgsConstructor
	public static class MockDto {
		Object targetObject;
		Class<?> mockClass;
	}

	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum MockTypeEnum {
		MOCK_BEFORE(1), MOCK_AFTER(2);
		private int type;
	}

}