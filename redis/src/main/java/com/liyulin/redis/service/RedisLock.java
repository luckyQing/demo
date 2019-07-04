package com.liyulin.redis.service;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

import com.liyulin.redis.constants.RedisKeysPrefix;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 基于redis实现的分布式锁
 * 
 * <h3>使用例子</h3>
 * <pre>
 *	// 添加购物车加锁
 *	String lockName = RedisLock.getLockName("cart", userId, skuId);
 *	LockInfo lockInfo = null;
 *	try {
 *		// 1、获取锁（设置有效期60秒）
 *		lockInfo = redisLock.lock(lockName, 1000 * 60);
 *		if (lockInfo.isFail()) {
 *			// 2.1、获取失败逻辑处理
 *			// ......
 *		} else {
 *			// 2.2、获取成功后逻辑处理
 *			// ......
 *		}
 *	} finally {
 *		// 3、释放锁
 *		redisLock.unlock(lockInfo);
 *	}
 * </pre>
 *
 * @author liyulin
 * @date 2018年10月17日下午10:45:46
 */
@Slf4j
@ConditionalOnBean(RedisTemplate.class)
@Configuration
public class RedisLock {

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	/** 释放锁lua脚本 */
	private static final String UNLOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
	private static final byte[] UNLOCK_LUA_BYTE = UNLOCK_LUA.getBytes();

	/**
	 * 获取（非阻塞）锁
	 * 
	 * <p>
	 * 未获取成功，不会阻塞当前线程
	 * 
	 * @param lockName 锁名称
	 * @param expireMillis 锁有效期（单位：毫秒）
	 * @return
	 */
	public LockInfo lock(String lockName, long expireMillis) {
		return lock(lockName, expireMillis, 0);
	}

	/**
	 * 获取（阻塞）锁（重复获取时间间隔10毫秒）
	 * 
	 * <p>
	 * 未获取成功会阻塞当前线程，直至获取成功或失败。 阻塞时间为acquireTimeoutMillis，重复获取锁的时间间隔为10毫秒
	 * 
	 * @param preKey 锁的key值
	 * @param lockName 锁名称
	 * @param expireMillis 锁有效期（单位：毫秒）
	 * @param acquireTimeoutMillis 获取锁超时时间（单位：毫秒）
	 * @return
	 */
	public LockInfo lock(String lockName, long expireMillis, long acquireTimeoutMillis) {
		return lock(lockName, expireMillis, 10, acquireTimeoutMillis);
	}

	/**
	 * 获取（阻塞）锁
	 * 
	 * <p>
	 * 未获取成功会阻塞当前线程，直至获取成功或失败。 阻塞时间为acquireTimeoutMillis，重复获
	 * s取锁的时间间隔为retryIntervalTimeMillis
	 * 
	 * @param lockName 锁名称
	 * @param expireMillis 锁有效期（单位：毫秒）
	 * @param retryIntervalTimeMillis 重复获取锁的时间间隔（单位：毫秒）
	 * @param acquireTimeoutMillis 获取锁超时时间（单位：毫秒）
	 * @return
	 */
	public LockInfo lock(String lockName, long expireMillis, long retryIntervalTimeMillis, long acquireTimeoutMillis) {
		// 重试次数
		long maxRetryCount = acquireTimeoutMillis / retryIntervalTimeMillis;
		String lockKey = getLockKey(lockName);
		String identifier = UUID.randomUUID().toString();
		for (int i = 0; i <= maxRetryCount; i++) {
			if (setNX(lockKey, identifier, expireMillis)) {
				return new LockInfo(lockKey, identifier, true);
			}
			if (i < maxRetryCount) {
				try {
					TimeUnit.MILLISECONDS.sleep(retryIntervalTimeMillis);
				} catch (InterruptedException e) {
					log.error("lock.error", e);
				}
			}
		}

		return new LockInfo(null, null, false);
	}

	/**
	 * 释放锁
	 * 
	 * @param lockInfo 锁信息
	 * @return 如果获取锁失败，直接返回{@code false}
	 */
	public boolean unlock(final LockInfo lockInfo) {
		if (null == lockInfo || lockInfo.isFail()) {
			return false;
		}
		if (StringUtils.isBlank(lockInfo.getLockKey())
				|| StringUtils.isBlank(lockInfo.getLockValue())) {
			return false;
		}

		Long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.eval(UNLOCK_LUA_BYTE, ReturnType.INTEGER, 1,
						lockInfo.getLockKey().getBytes(), lockInfo.getLockValue().getBytes());
			}
		}, true);

		return (null == result) ? false : (result > 0);
	}

	/**
	 * set if not exist
	 * 
	 * @param key
	 * @param value
	 * @param expireMillis
	 * @return set成功，返回{@code true}；set失败，返回{@code false}
	 */
	private boolean setNX(final String key, final String value, long expireMillis) {
		Boolean success = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.set(key.getBytes(), value.getBytes(),
						Expiration.milliseconds(expireMillis), SetOption.SET_IF_ABSENT);
			}
		}, true);

		return (null == success) ? false : success;
	}

	/**
	 * 获取lockKey
	 * 
	 * @param lockName
	 * @return
	 */
	private static String getLockKey(String lockName) {
		return RedisKeysPrefix.LOCK + lockName;
	}

	/**
	 * 获取lockName
	 * 
	 * @param agrs lockName的组成参数
	 * @return
	 */
	public static String getLockName(String... agrs) {
		if (ArrayUtils.isEmpty(agrs)) {
			throw new NullPointerException("lockName agrs不能为空！");
		}
		StringBuilder lockName = new StringBuilder();
		for (int i = 0, size = agrs.length; i < size; i++) {
			lockName.append(agrs[i]);
			if (i < size - 1) {
				lockName.append(RedisKeysPrefix.SEPARATOR);
			}
		}

		return lockName.toString();
	}

	/** 锁信息 */
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public class LockInfo {
		/** 锁名 */
		private String lockKey;
		/** 锁值 */
		private String lockValue;
		/** 是否成功 */
		private boolean success;

		/**
		 * 是否失败
		 * 
		 * @return 如果失败，返回{@code true}；如果成功，返回{@code false}
		 */
		public boolean isFail() {
			return !success;
		}

	}

}
