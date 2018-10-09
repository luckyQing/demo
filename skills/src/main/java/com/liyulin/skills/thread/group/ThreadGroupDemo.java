package com.liyulin.skills.thread.group;

/**
 * 线程组
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class ThreadGroupDemo {

	public static void main(String[] args) {
		ThreadGroup frist = new ThreadGroup("frist");
		ThreadGroup second = new ThreadGroup(frist, "second");
		ThreadGroup third = new ThreadGroup(second, "third");
		new Thread5(frist, "one");
		new Thread6(third, "two");
	}
}

class Thread5 extends Thread {
	Thread5(ThreadGroup g, String name) {
		super(g, name);
	}
}

class Thread6 extends Thread5 {
	Thread6(ThreadGroup g, String name) {
		super(g, name);
		start();
	}

	public void run() {
		ThreadGroup group = getThreadGroup().getParent().getParent();
		group.list();
	}
}
