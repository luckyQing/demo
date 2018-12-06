package com.liyulin.webservice.service;

import javax.jws.WebService;

import com.liyulin.webservice.response.UserRespBody;

@WebService
public interface IUserService {

	UserRespBody getUser(String userId);

	String getUserName(String userId);

}