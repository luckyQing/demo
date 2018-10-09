package com.liyulin.skills.grammar.override;

/**
 * 此程序不能编译
 * 
 * @author liyulin
 * @version 1.0 11/07/2013
 */
public class PrivateMatter {

	public static void main(String[] args) {
		// System.out.println(new Derived().className);
	}

}

class Base {
	public String className = "Base";
}

/**
 * 此处的className隐藏了Base中的Derived。 同时此处的className是private的，不可访问
 */
class Derived extends Base {
	@SuppressWarnings("unused")
	private String className = "Derived";
}
