package com.liyulin.skills.file;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 解析网页
 * 
 * @author liyulin
 * @version 1.0 09/07/2013
 */
public class ParseHtml {

	public static Collection<String> getURLCollection(String urlString) {
		URL url = null;
		URLConnection conn = null;
		List<String> urlCollection = new ArrayList<String>();
		try {
			url = new URL(urlString);
			conn = url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			InputStreamReader in = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(in);
			String nextLine = br.readLine();

			while (nextLine != null) {
				urlCollection.add(nextLine);
				nextLine = br.readLine();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return urlCollection;
	}

	public static void main(String[] args) {
		for (String s : getURLCollection("http://user.qzone.qq.com/1634753825/infocenter")) {
			System.out.println(s);
		}
	}

}
