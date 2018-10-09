package com.liyulin.skills.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取IP地址相关信息
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class InetAddressDemo {

	public static void main(String[] args) throws UnknownHostException {
		// 获取本机地址信息
		InetAddress IP0 = InetAddress.getLocalHost();
		System.out.println("IP0.getCanonicalHostName() = " + IP0.getCanonicalHostName());
		System.out.println("IP0.getHostAddress = " + IP0.getHostAddress());
		System.out.println("IP0.getHostName = " + IP0.getHostName());
		System.out.println("IP0.toString() = " + IP0.toString());
		System.out.println("=========================================================");
		// 获取指定域名地址信息
		InetAddress IP1 = InetAddress.getByName("www.baidu.com");
		System.out.println("IP1.getCanonicalHostName() =" + IP1.getCanonicalHostName());
		System.out.println("IP1.getHostAddress() =" + IP1.getHostAddress());
		System.out.println("IP1.getHostName() =" + IP1.getHostName());
		System.out.println("IP1.toString() =" + IP1.toString());
		System.out.println("=========================================================");
		// 比较两个InetAddress
		InetAddress IP2 = InetAddress.getByAddress(IP1.getAddress());
		System.out.println("IP2.getCanonicalHostName() =" + IP2.getCanonicalHostName());
		System.out.println("IP2.getHostAddress() =" + IP2.getHostAddress());
		System.out.println("IP2.getHostName() =" + IP2.getHostName());
		if (IP2.equals(IP1)) {
			System.out.println("IP2 equals IP1");
		} else {
			System.out.println("IP2 not equals IP1");
		}
	}
	
}
