package com.liyulin.design.patterns.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDao implements IUserDao {

	@Override
	public void save() {
		log.info("----已经保存数据!----");
	}

	@Override
	public boolean update() {
		log.info("----已经更新数据!----");
		return false;
	}

}
