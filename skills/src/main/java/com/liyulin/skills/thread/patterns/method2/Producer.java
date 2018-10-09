package com.liyulin.skills.thread.patterns.method2;

/**
 * 生产者
 * 
 * @author liyulin
 * @date 2013/07/13
 */
public class Producer implements Runnable {

	private Store store;

	public Producer(Store store) {
		this.store = store;
	}

	@Override
	public void run() {
		synchronized (store) {
			int count = 0;
			while (true) {
				try {
					// 判断仓库容量是否满了
					if (store.getCurNum() < store.getSunNum()) {// 未满
						Iphone iphone = new Iphone();
						iphone.setName("iphone" + (++count));
						iphone.setColor("白色" + count);

						// 将商品添加到仓库
						store.addIphone(iphone);
						// 释放当前锁store
						store.notify();
					} else {
						System.out.println("您的仓库已经满了，请等待消费...");
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
