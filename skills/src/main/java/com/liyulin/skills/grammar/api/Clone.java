package com.liyulin.skills.grammar.api;

import java.util.Date;

/**
 * 1、外部引用可以通过访问器改变一个类的私有方法 2、克隆可以避免这种问题
 *
 * @author liyulin
 * @date 2013-03-02
 */
public class Clone {

	/**
	 * @param args
	 */
	private Date hireday;

	@SuppressWarnings("deprecation")
	public Clone() {
		hireday = new Date(2012, 12, 30);
		System.out.println("初始值-日期：" + hireday);
	}

	// 直接返回私有变量的引用
	public Date getHireday() {
		return hireday;
	}

	// 通过克隆返回私有变量的值
	public Date getCloneHiredays() {
		return (Date) hireday.clone();
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/************************** 引用 *************************/
		System.out.println("***************应用引用：***************");
		Clone test1 = new Clone();
		Date d1 = test1.getHireday();
		d1.setDate(12);
		System.out.println("更改后-日期：" + test1.getHireday());
		/************************** 克隆 *************************/
		System.out.println("\n***************应用克隆：***************");
		Clone test2 = new Clone();
		Date d2 = test2.getCloneHiredays();
		d2.setDate(12);
		System.out.println("更改后-日期：" + test2.getHireday());
		System.out.println("d2.getDate()：" + d2.getDate());
	}
}
