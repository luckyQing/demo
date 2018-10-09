package com.liyulin.skills.grammar.innerClass;

/***
 * 创建内部类的方式
 */
public class NewInnerClass {

	public NewInnerClass() {
		System.out.println("Outer class has been builded!");
	}

	public class Inner {
		public Inner() {
			System.out.println("Inner class has been builded!");
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		NewInnerClass dn = new NewInnerClass();
		NewInnerClass.Inner dni = dn.new Inner();
	}
	
}
