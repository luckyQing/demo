package com.liyulin.skills.grammar.innerClass;

/**
 * “双绞线”问题分析：私有成员不会被继承。在这个程序中，name方法并没有被继承到reproduce方法中
 * 的匿名类中。所以，匿名类中对于printName方法的调用必须关联到外围（"main"）实例而不是 当前（"reproduce"）实例。
 * 
 * @author liyulin
 * @version 1.0 14/07/2013
 */
public class Twisted {

	private String name;

	public Twisted(String name) {
		this.name = name;
	}

	// private
	private String name() {
		return name;
	}

	private void reproduce() {
		new Twisted("reproduce") {
			void printName() {
				System.out.println(name());
			}
		}.printName();
	}

	public static void main(String[] args) {
		new Twisted("main").reproduce();
	}
}
