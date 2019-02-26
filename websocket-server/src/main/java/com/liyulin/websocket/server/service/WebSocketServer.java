package com.liyulin.websocket.server.service;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint(value = "/")
@Service
@Slf4j
public class WebSocketServer {

	@OnOpen
	public void onOpen(Session session) {
		log.info("onOpen sessionId={}", session.getId());
	}

	@OnClose
	public void onClose(Session session) {
		log.info("onClose sessionId={}", session.getId());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		log.info("onMessage sessionId={}; message={}", session.getId(), message);
		session.getBasicRemote().sendText("服务端已接收到消息"+message);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		log.error("onError sessionId={}; error={}", session.getId(), error);
	}

}