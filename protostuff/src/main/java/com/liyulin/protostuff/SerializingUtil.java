package com.liyulin.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class SerializingUtil {

	/**
	 * 将目标类序列化为byte数组
	 *
	 * @param source
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> byte[] serialize(T source) {
		Schema<T> schema = RuntimeSchema.getSchema((Class<T>) source.getClass());
		LinkedBuffer linkedBuffer = null;
		try {
			linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
			return ProtostuffIOUtil.toByteArray(source, schema, linkedBuffer);
		} finally {
			if (linkedBuffer != null) {
				linkedBuffer.clear();
			}
		}
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
	public static <T> T deserialize(byte[] source, Class<T> typeClass) {
		Schema<T> schema = RuntimeSchema.getSchema(typeClass);
		T newInstance = schema.newMessage();
		ProtostuffIOUtil.mergeFrom(source, newInstance, schema);

		return newInstance;
	}

}