package com.liyulin.design.patterns.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
	public static void main(String[] args) {
		// System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

		IUserDao target = new UserDao();
		log.info("target1：{}", target.getClass());

		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		log.info("target2：{}", target.getClass());

		proxy.save();
		proxy.update();
	}
}
