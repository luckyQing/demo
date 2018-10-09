package com.liyulin.skills.util.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 控制并发线程的数量
 *
 * @author luckytom
 * @version 1.0 2017年12月19日 下午11:27:31
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		int parties = 10;
		final Semaphore semaphore = new Semaphore(parties);
		for (int i = 0; i < parties; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("do something 1.");
						semaphore.tryAcquire(10, TimeUnit.SECONDS);
						System.out.println("do something "+semaphore.availablePermits());
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						semaphore.release();
					}
				}
			}).start();
		}
	}

}
