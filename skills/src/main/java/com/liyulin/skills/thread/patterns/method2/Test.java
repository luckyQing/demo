package com.liyulin.skills.thread.patterns.method2;

/**
 * 测试类
 * 
 * @author liyulin
 * @date 2013/07/13
 */
public class Test {

	public static void main(String[] args) {
		Store store = new Store();

		Thread t = new Thread(new Producer(store));
		t.start();

		Customer customer = new Customer(store);
		customer.start();
	}

}