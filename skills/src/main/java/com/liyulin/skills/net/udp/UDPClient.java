package com.liyulin.skills.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import lombok.extern.slf4j.Slf4j;

/**
 * UDP客户端
 * 
 * @author liyulin
 * @date 2013/03/08
 */
@Slf4j
public class UDPClient {

	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		try (DatagramSocket datagramSocket = new DatagramSocket(5000);) {
			log.info("客户端正在等待服务器端发送数据....");
			while (true) {
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(datagramPacket);
				log.info("来自主机：{}，端口号：{}", datagramPacket.getAddress().toString(), datagramPacket.getPort());

				String psx = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				log.info(psx);
				if ((psx == null) || (psx.equals("end"))) {
					break;
				}
			}
		} catch (IOException e) {
			log.error("IOException", e);
		}
	}
	
}