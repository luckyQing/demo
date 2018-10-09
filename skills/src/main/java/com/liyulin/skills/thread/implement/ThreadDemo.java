package com.liyulin.skills.thread.implement;

/**
 * 方式二：继承Thread类
 * 
 * @author liyulin
 * @date 2013/01/19
 */
public class ThreadDemo extends Thread {
	private String name;

	public ThreadDemo(String name) {
		this.name = name;
		this.start();
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("<<<<" + name);
		}
	}

	public static void main(String[] args) {
		new ThreadDemo("线程1");
		new ThreadDemo("线程2");
		new ThreadDemo("线程3");
	}
}
