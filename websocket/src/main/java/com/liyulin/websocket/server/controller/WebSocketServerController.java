package com.liyulin.websocket.server.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketServerController {

	@MessageMapping("/say")
	public String say(String content) {
		return "接受到：" + content;
	}

}