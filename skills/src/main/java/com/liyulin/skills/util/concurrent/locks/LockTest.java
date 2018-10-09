package com.liyulin.skills.util.concurrent.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static int size = 10;

	public static void main(String[] args) {
		for (int i = 0; i < size; i++) {
			new LockThread(true).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("===================");
		LockThread.num=0;
		for (int i = 0; i < size; i++) {
			new LockThread(false).start();
		}
	}

}

class LockThread extends Thread {
	public static int num = 0;
	private static final Lock lock = new ReentrantLock();
	private boolean isSyn;

	public LockThread(boolean isSyn) {
		this.isSyn = isSyn;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(isSyn){
			lock();
		} else {
			unlock();
		}
	}
	
	public void unlock(){
		doSomething();
	}
	
	private void lock(){
		lock.lock();
		try {
			doSomething();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	private void doSomething(){
		num = num + 1;
		if (num == LockTest.size) {
			System.err.println(num);
		} else {
			System.out.println(num);
		}
	}
}
