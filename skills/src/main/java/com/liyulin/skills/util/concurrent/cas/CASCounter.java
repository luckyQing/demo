package com.liyulin.skills.util.concurrent.cas;

import com.liyulin.skills.reflect.unsafe.UnsafeUtils;

import sun.misc.Unsafe;

public class CASCounter {
	private volatile long value = 0;
	private static final Unsafe unsafe = UnsafeUtils.getUnsafe();
	private static long valueOffset;

	static {
		try {
			valueOffset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("value"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	public CASCounter() {Thread.yield();
	}

	public CASCounter(long counter) {
		this.value = counter;
	}

	public void increment() {
		while (!unsafe.compareAndSwapLong(this, valueOffset, value, value + 1));
	}

	public long getCounter() {
		return value;
	}
}
