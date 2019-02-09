package com.liyulin.jws.webservice.server.service;

import javax.jws.WebService;

@WebService
public interface IJwsService {

	public int add(int a, int b);

	public int minus(int a, int b);
	
}