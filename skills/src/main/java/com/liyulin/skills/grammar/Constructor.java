package com.liyulin.skills.grammar;

/**
 * 默认构造方法的修饰符是与所在类的修饰符一致的，也就是说如果类是public的， 构造方法的修饰符就是public的；如果类无修饰符，默认构造方法也将没有
 * 修 饰符。
 * 
 * @author liyulin
 * @version 1.0 07/07/2013
 */
class Constructor {

	/** 数字 */
	@SuppressWarnings("unused")
	private int num = 3;

	// public Constructor(){
	//
	// }

	// private Constructor(){
	//
	// }

	public static void main(String[] args) {
		Constructor constructor = new Constructor();

		for (Object obj : constructor.getClass().getDeclaredConstructors()) {
			System.out.println(obj);
		}
	}

}
