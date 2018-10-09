package com.liyulin.design.patterns.singleton;

/**
 * 单例模式（实现方式二） 
 * 特点：在加载类的时候创建对象，这种是线程安全的。
 * 
 * @author liyulin
 * @version 1.0 2013-9-6 下午7:13:44
 */
public class SingletonDemo2 {

	private static SingletonDemo2 singletonDemo2 = null;

	// 3、控制外部调用构造方法的次数
	static {
		singletonDemo2 = new SingletonDemo2();
	}

	// 1、回收外部能够new SingletonDemo2()的权限
	private SingletonDemo2() {
		
	}

	// 2、主动给外部提供自己的实例
	public static SingletonDemo2 getInstance() {
		return singletonDemo2;
	}
}
