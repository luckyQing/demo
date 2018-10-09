package com.liyulin.skills.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载网络资源
 * 
 * @author liyulin
 * @version 1.0 09/07/2013
 */
public class DownLoad {

	/** 从指定网址下载文件 */
	public static void down(String urlAddr) {
		InputStream in = null;
		FileOutputStream out = null;

		try {
			URL url = new URL(urlAddr);
			URLConnection urlConn = url.openConnection();
			urlConn.connect();

			in = urlConn.getInputStream();
			String filePath = url.getFile();// 获得完整路径

			@SuppressWarnings("unused")
			int pos = filePath.lastIndexOf("/");//
//			String fileName = filePath.substring(pos + 1);// 截取文件名
			String fileName = "POI3.8 API.chm";
			out = new FileOutputStream("D:/" + fileName);

			byte[] bytes = new byte[1024];
			int len = in.read(bytes);

			while (len != -1) {
				out.write(bytes, 0, len);
				len = in.read(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		DownLoad.down("http://dldx.csdn.net/fd.php?i=888544658627062&s=47c0e53d0664bb44e69f1674bf1c4f23");
	}

}
