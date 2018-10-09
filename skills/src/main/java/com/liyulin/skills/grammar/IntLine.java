package com.liyulin.skills.grammar;

/**
 * 在Java7中，数值字面量，不管是整数还是浮点数，都允许在数字之间插入任意多个下划线。这些下划线不会对字面量的数值产生影响，其目的主要是方便阅读。
 * 最基本的原则是下划线只能出现在数字中间，也就是说前后都必须是数字。
 * 
 * @author liyulin
 * @version 1.0 16/07/2013
 */
public class IntLine {

	public static void main(String[] args) {
		int val1 = 12_0400;
		int val2 = 21_3000;
		System.out.println(val1 + val2);
	}

}
