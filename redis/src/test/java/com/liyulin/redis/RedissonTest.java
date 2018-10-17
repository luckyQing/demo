package com.liyulin.redis;

import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class RedissonTest {
	
	@Autowired
	private Redisson redisson;
	int sum =0;
	
	/**
	 * 可重入锁
	 * @throws InterruptedException
	 */
	@Test
	public void testRLock() throws InterruptedException {
		RLock lock = redisson.getLock("anyLock");
		int count = 5;
		for (int i = 0; i < count; i++) {
			new Thread(() -> {
				try {
					lock.lock();
					sum++;
					log.info("sum={}", sum);
				} finally {
					lock.unlock();
				}
			}).start();
		}
		TimeUnit.SECONDS.sleep(5);
		Assertions.assertThat(sum).isEqualTo(count);
	}
	
	/**
	 * 联锁
	 */
	@Test
	public void testRedissonMultiLock() {
		RLock lock1 = redisson.getLock("lock1");
		RLock lock2 = redisson.getLock("lock2");
		RLock lock3 = redisson.getLock("lock3");

		RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
		// 同时加锁：lock1 lock2 lock3
		// 所有的锁都上锁成功才算成功。
		try {
			lock.lock();
		} finally {
			lock.unlock();
		}
	}
	
}
