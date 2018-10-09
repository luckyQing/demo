package com.liyulin.skills.util.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author luckytom
 * @version 1.0 2017年12月19日 下午11:54:15
 */
public class ReadWriteLockTest {
	private static final Map<String, Object> CACHE_MAP = new HashMap<String, Object>();
	private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private static final Lock READ_LOCK = rwl.readLock();
	private static final Lock WRITE_LOCK = rwl.writeLock();

	public static Object get(String key) {
		READ_LOCK.lock();
		try {
			return CACHE_MAP.get(key);
		} finally {
			READ_LOCK.unlock();
		}
	}

	public static Object put(String key, Object o) {
		WRITE_LOCK.lock();
		try {
			return CACHE_MAP.put(key, o);
		} finally {
			WRITE_LOCK.unlock();
		}
	}
}
