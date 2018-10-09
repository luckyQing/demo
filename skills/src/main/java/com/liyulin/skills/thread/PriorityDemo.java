package com.liyulin.skills.thread;

/**
 * 线程优先级
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class PriorityDemo {

	public static void main(String[] args) {
		Thread4 thread1 = new Thread4("frist");
		Thread4 thread2 = new Thread4("second");
		Thread4 thread3 = new Thread4("Third");
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MAX_PRIORITY - 1);
		thread3.setPriority(Thread.MAX_PRIORITY - 2);
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

class Thread4 extends Thread {
	Thread4(String name) {
		super(name);
	}

	public synchronized void run() {
		System.out.println(this.getName());
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(this.getName() + "结束");
	}
}
