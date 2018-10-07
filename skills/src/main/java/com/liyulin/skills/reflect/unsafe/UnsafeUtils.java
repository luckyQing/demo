package com.liyulin.skills.reflect.unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

import sun.misc.Unsafe;

/**
 * 获取Unsafe实例
 *
 * @author liyulin
 * @date 2018年10月7日下午9:28:23
 */
public final class UnsafeUtils {

	public static Unsafe getUnsafe() {
		return UnsafeHolder.unsafe;
	}

	private UnsafeUtils() {
	}

	private static class UnsafeHolder {

		private static final Unsafe unsafe = AccessController.doPrivileged(new PrivilegedAction<Unsafe>() {
			public Unsafe run() {
				try {
					Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
					unsafe.setAccessible(true);
					return (Unsafe) unsafe.get(null);
				} catch (NoSuchFieldException e) {
					throw new IllegalStateException(e);
				} catch (IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}
		});

	}

}