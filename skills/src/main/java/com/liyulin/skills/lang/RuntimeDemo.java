package com.liyulin.skills.lang;

import java.io.IOException;

/**
 * 运行网页
 * 
 * @author liyulin
 * @version 1.0 08/07/2013
 */
public class RuntimeDemo {
	
	public static void main(String[] args) {
		try {
			String web = "http://www.baidu.com";
			Runtime.getRuntime().exec("explorer.exe " + web);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
