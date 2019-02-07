package com.liyulin.webservice.service;

import javax.jws.WebService;

import com.liyulin.webservice.response.UserRespBody;

// targetNamespace，一般是接口的包名倒序
@WebService
public interface IUserService {

	UserRespBody getUser(String userId);

	String getUserName(String userId);

}