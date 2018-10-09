package com.liyulin.design.patterns.factory;

/**
 * 测试
 *
 * @author liyulin
 * @version 1.0 2013-9-6 下午8:36:17 
 */
public class Client {

	public static void main(String[] args) {
		Shop shop = MyFactory.createShop("ShopImpl1");
		shop.shopping();
	}

}
