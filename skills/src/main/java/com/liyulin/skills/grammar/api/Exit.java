package com.liyulin.skills.grammar.api;

/**
 * System.exit(0)方法将停止当前线程和所有其他当场死亡的线程
 */
public class Exit {

	public static void main(String[] args) {
		try {
			System.out.println("Hello world");
			System.exit(0);// System.exit(0)方法将停止当前线程和所有其他当场死亡的线程
		} finally {
			System.out.println("Goodbye world");
		}
	}
	
}
