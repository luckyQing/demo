package com.liyulin.skills.thread.implement;

/**
 * 方式一：实现Runnalbe接口
 * 
 * @author liyulin
 * @date 2013/01/19
 */
public class RunnableDemo implements Runnable {
	private String name;

	public RunnableDemo(String name) {
		this.name = name;
		Thread thread = new Thread(this);
		thread.start();
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("<<<<" + name);
		}
	}

	public static void main(String[] args) {
		new RunnableDemo("线程1");
		new RunnableDemo("线程2");
		new RunnableDemo("线程3");
	}

}
