package com.liyulin.skills.thread.patterns.method1;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产者消费者模式
 * 
 * @author liyulin
 * @date 2013/07/13
 */
public class ProducerAndConsumer {

	public static void main(String[] args) {
		Factory factory = new Factory();
		new Producer(factory);
		new Consumer(factory);
	}
}

@Slf4j
class Factory {
	int n;
	boolean valueSet = false;

	synchronized void get() {
		if (!valueSet) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		log.info(Thread.currentThread().getName() + "-Get:" + n);
		valueSet = false;
		notify();
	}

	synchronized void put(int n) {
		if (valueSet) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		this.n = n;
		valueSet = true;
		log.info(Thread.currentThread().getName() + "-Put:" + n);
		notify();
	}
}

class Producer implements Runnable {
	Factory factory;

	Producer(Factory factory) {
		this.factory = factory;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int k = 0;
		for (int i = 0; i < 5; i++) {
			factory.put(k++);
		}
	}
}

class Consumer implements Runnable {
	Factory factory;

	Consumer(Factory factory) {
		this.factory = factory;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			factory.get();
		}
	}
}
