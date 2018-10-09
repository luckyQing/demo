package com.liyulin.skills.grammar.api;

import java.util.Date;

/***
 * java中用printf()向控制台格式化输出
 */
public class Printf {

	public static void main(String[] args) {
		float a = 9.0f, b = 2.0f;
		System.out.printf("a/b = %-8.2f", a / b);

		System.out.printf("%tc", new Date());

		String str[] = { "ddd", "dfasdfa", "dfadgfsdgag" };
		System.out.println();
		for (String s : str) {
			System.out.printf("%-16s哈哈哈哈\n", s);
		}
	}
	
}
