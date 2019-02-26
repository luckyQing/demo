package com.liyulin.websocket.client.service;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@ClientEndpoint
public class WebsocketClient {

	@OnOpen
	public void onOpen(Session session) {
		log.info("[onOpen]id={}, queryString={}", session.getId(), session.getQueryString());
	}

	@OnMessage
	public void onMessage(String message) {
		log.info("[onMessage]message={}", message);
	}

	@OnError
	public void onError(Throwable t) {
		log.error("onError", t);
	}

}