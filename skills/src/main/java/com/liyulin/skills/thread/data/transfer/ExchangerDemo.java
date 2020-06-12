package com.liyulin.skills.thread.data.transfer;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();
		
		Thread thread1 = new Thread(() -> {
			try {
				int value1 = exchanger.exchange(1);
				System.out.println("thread1 value1="+value1);
				int value2 = exchanger.exchange(2);
				System.out.println("thread1 value2="+value2);
				int value3 = exchanger.exchange(3);
				System.out.println("thread1 value3="+value3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread thread2 = new Thread(() -> {
			try {
				int value1 = exchanger.exchange(11);
				System.out.println("thread2 value1="+value1);
				int value2 = exchanger.exchange(22);
				System.out.println("thread2 value2="+value2);
				int value3 = exchanger.exchange(33);
				System.out.println("thread2 value3="+value3);
			} catch (InterruptedException e) {
			}
		});

		thread1.start();
		thread2.start();
	}
	
}