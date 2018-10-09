package com.liyulin.design.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

public class SingletonDemo3 {
	private SingletonDemo3() {
	}

	private static class SingletonoDemo3Holder {
		private static Connection instance = new Connection();
	}

	public static Connection getInstance() {
		return SingletonoDemo3Holder.instance;
	}
}

@Slf4j
class Connection {
	public Connection() {
		log.info("Connection()");
	}
}
