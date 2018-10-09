package com.liyulin.skills.grammar.string;

public class StringDemo1 {

	public static void main(String[] args) {
		String s1 = new String("abc");// 此句代码产生后在内存中会产生几个对象？
		// 执行完第一句代码后，在内存中创建两个对象。
		// 分析：首先Java会根据"abc"在String Pool（字符串池）中查找"abc"，
		// 如果没有该字符串则在String Pool中创建一个对象。
		// 然后在通过new关键字在Java内存堆栈中创建一个"abc"对象，并且由s1变量指向它。
		
		String s2 = "abc";// 此句代码产生后在内存中会产生几个对象？
		// 在执行第二句代码时，Java会依据上面的顺序首先在String Pool中查找"abc"，如果
		// 找到有此项，则变量s2指向String Pool中的"abc"。
		
		String s3 = new String("abc");// 此句代码产生后在内存中会产生几个对象？		
		// 在执行第三句代码时，Java首先也会在String Pool中查找"abc"对象，然后在内存堆栈
		// 中创建一个新的对象"abc"并且s3变量指向它。

		System.out.println(s1 == s2);// s1是存储在堆栈中的对象，s2是常量池中的对象
		System.out.println(s1 == s3);// s1是存储在堆栈中的对象abc，s3也是存储在堆栈中的对象abc，但是此时是不同的两个对象
		System.out.println(s2 == s3);
		
		/*
		 * public String intern()返回字符串对象的规范化表示形式。 一个初始时为空的字符串池，它由类String私有地维护。
		 * 当调用 intern 方法时，如果池已经包含一个等于此String对象的字符串（该对象由 equals(Object)方法确定），
		 * 则返回池中的字符串。否则，将此String对象添加到池中，并且返回此String对象的引用。
		 */
		System.out.println(s1 == s1.intern());//s1是存储在堆栈中的对象，s1.intern()是常量池中的对象，就是此处的s2的内存地址
		
		System.out.println(s2 == s2.intern());
		System.out.println(s1.intern() == s3.intern());//均为常量池中的对象

	}

}
