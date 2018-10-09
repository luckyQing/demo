package com.liyulin.skills.grammar.string;

public class StringDemo2 {

	public static void main(String[] args) {
		String hello = "Hello";
		String lo = "lo";

		System.out.println(hello == ("Hel" + "lo"));        // true
		//在"+"两边都是常量字符串，则将两个字符串合并并且在String Pool中查找"hello"
		//并返回在String Pool中的内存地址正好也是hello变量的内存地址，所以第一句代码会输出true。
		
		System.out.println(hello == ("Hel" + lo));          // false
		//当结果不是一个编译时常量时，字符串串接运算符 + 会隐式new一个新的String对象。（见 Java语言规范57页 4.3.3String类）
		//hello是常量池中的对象，而("Hel" + lo)是堆栈中的对象，所以这句代码是输出false。


		//intern() 返回字符串对象的规范化表示形式。
		System.out.println(hello == ("Hel" + lo).intern()); // true 
	}

}
