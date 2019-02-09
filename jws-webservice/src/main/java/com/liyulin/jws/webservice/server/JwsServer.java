package com.liyulin.jws.webservice.server;

import javax.xml.ws.Endpoint;

import com.liyulin.jws.webservice.server.service.JwsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwsServer {

	public static void main(String[] args) {
		String address = "http://localhost:7777/ns";
		Endpoint.publish(address, new JwsServiceImpl());
		
		log.info("===启动完成===");
	}
	
}