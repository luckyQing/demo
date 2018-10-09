package com.liyulin.skills.grammar.innerClass;

/***
 * 内部类可以访问外部类中的方法
 */
public class InnerClassAccess {

	void f() {
		System.out.println("Innerclass.f()");
	}

	public class Inner {
		public InnerClassAccess outer() {
			return InnerClassAccess.this;
		}
	}

	public Inner inner() {
		return new Inner();
	}

	public static void main(String[] args) {
		InnerClassAccess dt = new InnerClassAccess();
		InnerClassAccess.Inner dti = dt.inner();
		dti.outer().f();
	}

}
