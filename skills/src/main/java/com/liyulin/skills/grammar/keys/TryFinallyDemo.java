package com.liyulin.skills.grammar.keys;

/***
 * 当finally子句包含return语句时，将会出现一种意想不到的结果。 假设利用return语句从try语句块中退出。在方法返回前，finally
 * 子句的内容将被执行。如果finally子句中也有一个return语句， 这个返回值将覆盖原始的返回值。
 */
public class TryFinallyDemo {

	// 返回值覆盖
	@SuppressWarnings("finally")
	public static int f(int n) {
		try {
			return n;
		} finally {
			return 0;
		}
	}

	// return结束本程序
	public static void f() {
		try {
			return;
		} finally {
			System.out.println("finally!");
		}
	}

	public static void main(String[] args) {
		System.out.println("TryFinally.f(10) = " + TryFinallyDemo.f(10));
		TryFinallyDemo.f();
	}
	
}
