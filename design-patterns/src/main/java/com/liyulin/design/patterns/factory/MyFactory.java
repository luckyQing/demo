package com.liyulin.design.patterns.factory;

/**
 * 工厂模式
 *
 * @author liyulin
 * @version 1.0 2013-9-6 下午8:52:02
 */
public class MyFactory {

	public static Shop createShop(String choice) {
		// 选择实现
		if (choice.equals("ShopImpl1")) {
			return new ShopImpl1();
		}
		if (choice.equals("ShopImpl2")) {
			return new ShopImpl2();
		}
		return null;
	}
}
