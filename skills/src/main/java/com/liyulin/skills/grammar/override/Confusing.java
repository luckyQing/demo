package com.liyulin.skills.grammar.override;

/**
 * Java的重载解析过程是分两阶段运行的。第一阶段选取所有可获得并且可应
 * 用的方法或构造器。第二阶段在第一阶段选取的方法或构造器中选取最精确 
 * 的一个。如果一个方法或构造器可以接受传递给另一个方法或构造器的 任
 * 何参数，那么我们就说第一个方法比第二个方法缺乏精确性。
 * 
 * @author liyulin
 * @version 1.0 11/07/2013
 */
public class Confusing {

	private Confusing(Object o) {
		System.out.println("Object");
	}

	private Confusing(double[] dArray) {
		System.out.println("double dArray");
	}

	public static void main(String[] args) {
		new Confusing(null);
	}

}
