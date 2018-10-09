package com.liyulin.design.patterns.singleton;

/**
 * 单例模式（实现方式一） 
 * 特点：在调用方法的时候创建对象，这种是线程不安全的。
 * 此种方式的应用：延迟加载
 * 
 * @author liyulin
 * @version 1.0 2013-9-6 下午7:13:44
 */
public class SingletonDemo1 {

	// 4、用来保存自己的类实例
	private static volatile SingletonDemo1 singletonDemo = null;

	// 1、收回外部能够new singletonDemo()的权限
	private SingletonDemo1() {

	}

	// 2、主动给外部调用自己的实例
	public static SingletonDemo1 getInstance() {
		// 3、控制内部调用构造函数的次数
		// 5、只在第一次调用的时候new一个
		if (singletonDemo == null) {
			synchronized (SingletonDemo1.class) {
				if(singletonDemo == null){
					singletonDemo = new SingletonDemo1();
				}
			}
		}
		return singletonDemo;
	}
}