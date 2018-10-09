package com.liyulin.skills.util.concurrent.locks;

import java.util.concurrent.TimeUnit;

/**
 * 线程同步
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class SynTest {

	private static final int COUNT_NUM = 20;

	public static void synTest() {
		Operation operation = new Operation();

		SynThread[] synThreads = new SynThread[COUNT_NUM];
		for (int i = 0; i < synThreads.length; i++) {
			synThreads[i] = new SynThread(operation, true);
		}

		for (int i = 0; i < synThreads.length; i++) {
			synThreads[i].start();
		}
	}

	public static void test() {
		Operation operation = new Operation();

		SynThread[] synThreads = new SynThread[COUNT_NUM];
		for (int i = 0; i < synThreads.length; i++) {
			synThreads[i] = new SynThread(operation, false);
		}

		for (int i = 0; i < synThreads.length; i++) {
			synThreads[i].start();
		}
	}

	public static void main(String[] args) {
		synTest();
		test();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("synCount=" + Operation.synCount);
		System.out.println("count=" + Operation.count);
	}
}

class Operation {
	public static int synCount = 0;
	public static int count = 0;

	synchronized void synPlus() {
		synCount++;
	}

	void plus() {
		count++;
	}

}

class SynThread extends Thread {
	private Operation operation;
	private boolean isSyn;

	public SynThread(Operation operation, boolean isSyn) {
		this.operation = operation;
		this.isSyn = isSyn;
	}

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (isSyn) {
			operation.synPlus();
		} else {
			operation.plus();
		}
	}
}
