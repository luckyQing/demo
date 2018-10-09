package com.liyulin.skills.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ThreadPoolTest {

	/**
	 * 创建一个可缓存的线程池。
	 * 如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
	 * 此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
	 * newCachedThreadPool会首先在线程池中选择一个已有空闲线程来执行任务，如果线程池中没有空闲线程，它便会创建一个新的线程来执行任务。
	 */
	@Test
	public void cachedThreadPool() {
		// ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		ExecutorService cachedThreadPool = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		for (int i = 0; i < 10; i++) {
			cachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}

			});
		}
		cachedThreadPool.shutdown();
		try {
			cachedThreadPool.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}

	/**
	 * 创建一个大小无限的线程池。 此线程池支持定时以及周期性执行任务的需求。
	 */
	@Test
	public void scheduledThreadPool() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {

			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
		scheduledThreadPool.shutdown();
	}

	/**
	 * 创建一个单线程的线程池。 这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
	 * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
	 */
	@Test
	public void singleThreadExecutor() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		singleThreadExecutor.shutdown();
	}

	/**
	 * 创建固定大小的线程池。 每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
	 * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
	 */
	@Test
	public void fixedThreadPool() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		fixedThreadPool.shutdown();
	}

}