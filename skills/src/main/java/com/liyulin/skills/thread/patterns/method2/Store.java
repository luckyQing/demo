package com.liyulin.skills.thread.patterns.method2;

/**
 * 仓库类：iphone手机Bean
 * 
 * @author liyulin
 * @date 2013/07/13
 */
public class Store {

	// 仓库总容量
	private int sunNum = 20;
	// 当前数量
	private int curNum = 0;

	Iphone[] iphones = new Iphone[20];

	public int getSunNum() {
		return sunNum;
	}

	public int getCurNum() {
		return curNum;
	}

	/** 生产手机 */
	public void addIphone(Iphone iphone) {
		if (curNum < 20) {
			iphones[curNum++] = iphone;

			System.out.println("生产手机一台，名称：" + iphone.getName());
			System.out.println("生产：目前仓库容量为：" + this.getCurNum());
		}
	}

	/** 消费手机 */
	public Iphone removeIphone() {
		Iphone iphone = null;
		if (curNum > 0) {
			curNum--;// 当前数量减一
			iphone = iphones[curNum];
			iphones[curNum] = null;// 移除商品

			System.out.println("消费手机一台，名称是：" + iphone.getName());
			System.out.println("消费：目前仓库容量为：" + this.getCurNum());
		}
		return iphone;
	}
}
