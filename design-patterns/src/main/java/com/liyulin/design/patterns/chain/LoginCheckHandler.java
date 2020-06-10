package com.liyulin.design.patterns.chain;

public class LoginCheckHandler extends AbstractHandler {

	@Override
	public void doHandler(LoginReqVO loginReqVO) {
		if ("zhangsan".equals(loginReqVO.getUsername()) && "123456".equals(loginReqVO.getPwd())) {
			System.out.println("登陆成功");
			doNextHandler(loginReqVO);
		} else {
			throw new IllegalArgumentException("登陆失败！");
		}
	}

}