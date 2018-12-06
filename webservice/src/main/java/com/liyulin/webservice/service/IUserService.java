package com.liyulin.webservice.service;

import javax.jws.WebService;

import com.liyulin.webservice.response.UserDto;

@WebService
public interface IUserService {

	UserDto getUser(String userId);

	String getUserName(String userId);

}