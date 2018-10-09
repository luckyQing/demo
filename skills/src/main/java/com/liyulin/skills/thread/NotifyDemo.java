package com.liyulin.skills.thread;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class NotifyDemo {
	public static final int THREAD_NUM = 20;

	@Test
	public void notifyTest() {
		AtomicInteger done = new AtomicInteger(0);
		for (int i = 0; i < THREAD_NUM; i++) {
			new NotifyThread("线程" + i, done).start();
		}
		synchronized (done) {
			try {
				done.wait(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.err.println("all is done.");
	}

}

class NotifyThread extends Thread {
	private String threadName;
	private AtomicInteger done;

	public NotifyThread(String threadName, AtomicInteger done) {
		super();
		this.threadName = threadName;
		this.done = done;
	}

	@Override
	public void run() {
		super.run();
		System.out.println(threadName);
		int doneCount = done.incrementAndGet();
		if (doneCount == NotifyDemo.THREAD_NUM) {
			synchronized (done) {
				done.notify();
			}
			System.out.println("======>" + threadName);
		}
	}
}
