package com.liyulin.design.patterns.proxy.staticProxy;

public class UserDaoProxy implements IUserDao {
	private IUserDao target;

	public UserDaoProxy(IUserDao target) {
		this.target = target;
	}

	@Override
	public void save() {
		target.save();
	}

}
