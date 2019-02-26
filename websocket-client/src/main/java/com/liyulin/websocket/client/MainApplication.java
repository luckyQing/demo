package com.liyulin.websocket.client;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.websocket.client.service.WebsocketClientWapper;

@SpringBootApplication
@RestController
public class MainApplication {
	
	@Autowired
	private WebsocketClientWapper websocketClientWapper;
	
	@GetMapping("test")
	public String test() throws IOException {
		websocketClientWapper.getSession().getBasicRemote().sendText("测试");
		return "success";
	}

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
	
}