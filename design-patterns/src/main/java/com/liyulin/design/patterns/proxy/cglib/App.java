package com.liyulin.design.patterns.proxy.cglib;

public class App {
	public static void main(String[] args) {
		// System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:/cglib");
		UserDao target = new UserDao();
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		proxy.select();
		proxy.insert();
		proxy.update();
		proxy.delete();
	}
}
