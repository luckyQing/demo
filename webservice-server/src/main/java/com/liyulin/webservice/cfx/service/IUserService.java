package com.liyulin.webservice.cfx.service;

import javax.jws.WebService;

import com.liyulin.webservice.cfx.response.UserRespBody;

// targetNamespace，一般是接口的包名倒序
@WebService
public interface IUserService {

	UserRespBody getUser(String userId);

	String getUserName(String userId);

}