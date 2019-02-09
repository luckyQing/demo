package com.liyulin.jws.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;

import com.liyulin.jws.webservice.client.service.JwsServiceImpl;
import com.liyulin.jws.webservice.client.service.JwsServiceImplService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwsClient {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:7777/ns?wsdl");
		JwsServiceImplService service = new JwsServiceImplService(url);
		JwsServiceImpl jwsServiceImpl = service.getJwsServiceImplPort();

		log.info("1+2={}", jwsServiceImpl.add(1, 2));
		log.info("1-2={}", jwsServiceImpl.minus(1, 2));
	}

}