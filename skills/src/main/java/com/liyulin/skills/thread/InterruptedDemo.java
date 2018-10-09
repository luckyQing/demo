package com.liyulin.skills.thread;

/**
 * 线程中断
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class InterruptedDemo {

	public static void main(String[] args) throws Exception {
		Thread thread = new InterruptedThread();
		thread.start();
		System.in.read();
		Thread.interrupted();
		thread.join();
		System.out.println("线程退出！");
	}
}

class InterruptedThread extends Thread {
	public void run() {
		try {
			System.out.println("在20秒内按任意键中断线程！");
			sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
