package com.liyulin.websocket.client.service;

import java.io.IOException;
import java.net.URI;

import javax.annotation.PostConstruct;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WebsocketClientWapper {
	
	@Getter
	public Session session = null;
	@Autowired
	private WebsocketClient websocketClient;
	private String uri = "ws://localhost";

	@PostConstruct
	public void init() {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		log.info("Connecting to {}", uri);
		try {
			session = container.connectToServer(websocketClient, URI.create(uri));
		} catch (DeploymentException | IOException e) {
			log.error("websocket.connectToServer.error", e);
		}
	}

}