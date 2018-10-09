package com.liyulin.skills.thread;

/**
 * 线程运行状态
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class LifeThread {

	public static void main(String[] args) {
		LifeThread1 thread1 = new LifeThread1();
		System.out.println("等待状态[isAlive:" + thread1.isAlive() + "]");
		thread1.start();
		System.out.println("运行状态[isAlive:" + thread1.isAlive() + "]");
		try {
			thread1.join();
		} catch (InterruptedException e) {
		}
		System.out.println("线程结束[isAlive:" + thread1.isAlive() + "]");
	}
}

class LifeThread1 extends Thread {
	
	public void run() {
		int i = 0;
		while ((++i) < 100);
	}
	
}
