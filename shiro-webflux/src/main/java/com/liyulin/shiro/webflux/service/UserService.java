package com.liyulin.shiro.webflux.service;

import com.liyulin.shiro.webflux.entity.User;

/**
 * @author collin
 * @date 2021-05-24
 */
public interface UserService {

    User findByAccount(String account);

}