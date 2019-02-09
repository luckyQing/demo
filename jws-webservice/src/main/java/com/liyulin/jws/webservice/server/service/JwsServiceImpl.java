package com.liyulin.jws.webservice.server.service;

import javax.jws.WebService;

@WebService
public class JwsServiceImpl implements IJwsService {

	@Override
	public int add(int a, int b) {
		System.out.println(a + "+" + b + "=" + (a + b));
		return a + b;
	}

	@Override
	public int minus(int a, int b) {
		System.out.println(a + "-" + b + "=" + (a - b));
		return a - b;
	}

}