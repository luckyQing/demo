package com.liyulin.skills.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产者消费者简单模拟
 * 
 * @author liyulin
 * @date 2013/07/13
 */
public class ProducerAndCustomer {

	public static void main(String[] args) {
		Product product = new Product();
		new Producer(product);
		new Consumer(product);
	}
}

@Slf4j
class Product {
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
	Product product;

	Producer(Product product) {
		this.product = product;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int k = 0;
		for (int i = 0; i < 5; i++) {
			product.put(k++);
		}
	}
}

class Consumer implements Runnable {
	Product product;

	Consumer(Product product) {
		this.product = product;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			product.get();
		}
	}
}
