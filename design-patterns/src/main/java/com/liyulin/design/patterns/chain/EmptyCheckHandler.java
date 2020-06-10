package com.liyulin.design.patterns.chain;

public class EmptyCheckHandler extends AbstractHandler {

	@Override
	public void doHandler(LoginReqVO loginReqVO) {
		if (loginReqVO == null || loginReqVO.getUsername() == null || loginReqVO.getUsername() == ""
				|| loginReqVO.getPwd() == null || loginReqVO.getPwd() == "") {
			throw new IllegalArgumentException("请求参数不能为空！");
		}

		doNextHandler(loginReqVO);
	}

}