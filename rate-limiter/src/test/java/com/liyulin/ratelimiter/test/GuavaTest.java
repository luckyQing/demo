package com.liyulin.ratelimiter.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.ratelimiter.service.GuavaRateLimiter;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class GuavaTest extends TestCase {

	@Autowired
	private GuavaRateLimiter guavaRateLimiter;

	@Test
	public void testRateLimiter() {
		int qps = 20;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(qps);
		CountDownLatch latch = new CountDownLatch(qps);
		
		AtomicLong skuId = new AtomicLong(0);
		for (int i = 0; i < qps; i++) {
			new Thread(() -> {
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					log.error(e.getMessage(), e);
				}

				guavaRateLimiter.reduce(skuId.incrementAndGet());
				latch.countDown();
			}).start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

}