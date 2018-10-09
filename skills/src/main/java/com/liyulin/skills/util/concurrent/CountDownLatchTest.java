package com.liyulin.skills.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 等待多个线程完成
 *
 * @author luckytom
 * @version 1.0 2017年12月19日 下午11:26:42
 */
public class CountDownLatchTest {
	
	public static void main(String[] args) {
		countDownLatchTest();
		atomicIntegerTest();
	}
	
	public static void countDownLatchTest(){
		int threadSize = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
		for(int i=0; i<threadSize; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					countDownLatch.countDown();
				}
			}).start();
		}

		System.out.println("wait start====>");
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("wait end<====");
	}
	
	/**
	 * 通过AtomicInteger实现的方式，当线程体在wait()之前之前完时，会产生死锁！！！
	 */
	public static void atomicIntegerTest(){
		int threadSize = 10;
		final AtomicInteger countDownLatch = new AtomicInteger(threadSize);
		for(int i=0; i<threadSize; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
					
					if(countDownLatch.decrementAndGet()==0){
						synchronized (countDownLatch) {
							countDownLatch.notify();
						}
					}
				}
			}).start();
		}

		System.out.println("wait start====>");
		synchronized (countDownLatch) {
			try {
				countDownLatch.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("wait end<====");
	}
}
