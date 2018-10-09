package com.liyulin.skills.thread;

/**
 * 死锁
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class DeadLockDemo implements Runnable {

	private boolean flag;
	static Object obj1 = new Object();
	static Object obj2 = new Object();

	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " flag:" + flag);
		if (flag) {
			synchronized (obj1) {
				try {
					System.out.println(name + " 休眠500毫秒，等待obj2 ");
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (obj2) {
					System.out.println("获取obj2 ");
				}
			}
		} else {
			synchronized (obj2) {
				try {
					System.out.println(name + "休眠500毫秒，等待obj1 ");
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (obj1) {
					System.out.println("获取obj1 ");
				}
			}
		}
	}

	public static void main(String[] args) {
		DeadLockDemo obj1 = new DeadLockDemo();
		DeadLockDemo obj2 = new DeadLockDemo();
		obj1.flag = true;
		obj2.flag = false;
		Thread thread1 = new Thread(obj1);
		Thread thread2 = new Thread(obj2);
		thread1.start();
		thread2.start();
	}
}
