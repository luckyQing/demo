package com.liyulin.design.patterns.chain;

public class Test {

	public static void main(String[] args) {
		LoginReqVO loginReqVO = new LoginReqVO();
		loginReqVO.setUsername("zhangsan");
		loginReqVO.setPwd("123456");
		
		AbstractHandler.Builder builder = new AbstractHandler.Builder();
        builder.addHandler(new EmptyCheckHandler())
               .addHandler(new LoginCheckHandler());
        builder.build().doNextHandler(loginReqVO);
	}

}