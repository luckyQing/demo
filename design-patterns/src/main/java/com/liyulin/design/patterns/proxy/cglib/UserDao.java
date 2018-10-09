package com.liyulin.design.patterns.proxy.cglib;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDao {
	public void select() {
		log.info("----select!----");
	}

	protected void insert() {
		log.info("----insert!----");
	}

	private void selectList() {
		log.info("----selectList!----");
	}

	public final void update() {
		log.info("----update!----");
	}

	public static void delete() {
		log.info("----delete!----");
	}
}
