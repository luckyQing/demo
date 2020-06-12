package com.liyulin.skills.thread.data.transfer;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

	public static void main(String[] args) {
		SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
		Thread putThread = new Thread(() -> {
			try {
				queue.put(1);
				System.out.println("put from putThread1");
				queue.put(2);
				System.out.println("put from putThread2");
				queue.put(3);
				System.out.println("put from putThread3");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// 线程B takeThread
		Thread takeThread = new Thread(() -> {
			try {
				System.out.println("take from putThread: " + queue.take());
				System.out.println("take from putThread: " + queue.take());
				System.out.println("take from putThread: " + queue.take());
			} catch (InterruptedException e) {
			}
		});

		putThread.start();
		takeThread.start();
	}

}