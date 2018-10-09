package com.liyulin.skills.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP客户端
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class TCPClient {

	public void startClient() {
		try (// 第一步：创建服务端连接
				Socket socket = new Socket("127.0.0.1", 1200);
				// 第二步：创建输出流
				PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
				// 第三步：创建输入流
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), "utf-8"));) {
			printWriter.println("Hello World!");
			printWriter.flush();
			String str = bufferedReader.readLine();
			System.out.println("[客户端]" + str);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TCPClient().startClient();
	}
	
}
