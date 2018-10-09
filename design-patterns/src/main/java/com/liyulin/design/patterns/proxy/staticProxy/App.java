package com.liyulin.design.patterns.proxy.staticProxy;

public class App {

	public static void main(String[] args) {
		UserDao target = new UserDao();
		UserDaoProxy proxy = new UserDaoProxy(target);
		proxy.save();
	}

}
