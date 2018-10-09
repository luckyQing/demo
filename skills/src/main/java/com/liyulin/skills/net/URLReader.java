package com.liyulin.skills.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 读取网页内容
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class URLReader {

	public static void main(String[] args) throws Exception {
		URL baidu = new URL("http://www.baidu.com/");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(baidu.openStream()));) {
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
			}
		}
	}

}
