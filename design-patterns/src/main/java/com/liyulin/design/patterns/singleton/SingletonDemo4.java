package com.liyulin.design.patterns.singleton;

/**
 * 枚举实现单例模式
 *
 * @author luckytom
 * @version 1.0 2017年12月25日 上午9:44:42
 */
public final class SingletonDemo4 {
	private SingletonDemo4(){}
	
	public enum Holder{
		INSTANCE;
		private Object object;
		private Holder() {
			// do something, init eg.
			object = new Object();
		}

		public Object getObject() {
			return INSTANCE.object;
		}
	}
	
	public static Object getInstance(){
		return Holder.INSTANCE.getObject();
	}
}
