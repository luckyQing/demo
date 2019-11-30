package com.liyulin.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

public class SerializingUtil {
	
	/**
	 * 将目标类序列化为byte数组
	 *
	 * @param source
	 * @param <T>
	 * @return
	 */
	public static <T> byte[] serialize(T source) {
		RuntimeSchema<T> schema;
		LinkedBuffer buffer = null;
		byte[] result;
		try {
			schema = RuntimeSchema.createFrom((Class<T>) source.getClass());
			buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
			result = ProtostuffIOUtil.toByteArray(source, schema, buffer);
		} finally {
			if (buffer != null) {
				buffer.clear();
			}
		}

		return result;
	}

	/**
	 * 将byte数组反序列化为目标类
	 *
	 * @param source
	 * @param typeClass
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T deserialize(byte[] source, Class<T> typeClass)
			throws InstantiationException, IllegalAccessException {
		RuntimeSchema<T> schema = RuntimeSchema.createFrom(typeClass);
		T newInstance = typeClass.newInstance();
		ProtostuffIOUtil.mergeFrom(source, newInstance, schema);

		return newInstance;
	}
	
}