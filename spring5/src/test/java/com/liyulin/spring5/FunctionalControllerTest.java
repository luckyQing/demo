package com.liyulin.spring5;

import org.springframework.web.reactive.function.client.WebClient;

import junit.framework.TestCase;

public class FunctionalControllerTest extends TestCase {
	private WebClient client = WebClient.create("http://localhost:8080");
	
	public void testApp() {
		
	}
	
}