package com.liyulin.skills.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障
 *
 * @author luckytom
 * @version 1.0 2017年12月19日 下午11:26:55
 */
public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		int parties = 10;
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(parties);
		for (int i = 0; i < parties; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + " await start");
					try {
						cyclicBarrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " await end");
				}
			}).start();
		}
	}
	
}
