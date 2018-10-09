package com.liyulin.skills.net;

import java.net.URL;

/**
 * 读取URL相关信息
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class URLDemo {

	public static void main(String[] args) throws Exception {
		URL Aur1 = new URL("http://java.sun.com/docs/books/");
		URL tuto = new URL(Aur1, "tutorial.intro.html#DOWNLOADING");
		System.out.println("protocol = " + tuto.getProtocol());
		System.out.println("host = " + tuto.getHost());
		System.out.println("filename = " + tuto.getFile());
		System.out.println("prot = " + tuto.getPort());
		System.out.println("ref = " + tuto.getRef());
		System.out.println("query = " + tuto.getQuery());
		System.out.println("path = " + tuto.getPath());
	}
	
}
