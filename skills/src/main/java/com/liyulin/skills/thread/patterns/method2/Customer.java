package com.liyulin.skills.thread.patterns.method2;

/**
 * 消费者
 * 
 * @author liyulin
 * @date 2013/07/13
 */
public class Customer extends Thread {
	private Store store;

	public Customer(Store store) {
		this.store = store;
	}

	@Override
	public void run() {
		synchronized (store) {
			while (true) {
				try {
					// 判断仓库是否有产品
					if (store.getCurNum() > 0) {// 未满
						// 消费商品
						store.removeIphone();
						// 释放当前锁store
						store.notify();
					} else {
						System.out.println("您的仓库为空，请等待生产...");
						store.wait();// 等待
					}

					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
