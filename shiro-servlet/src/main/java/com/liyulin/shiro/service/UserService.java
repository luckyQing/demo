package com.liyulin.shiro.service;

import com.liyulin.shiro.entity.User;

/**
 * @author collin
 * @date 2021-05-24
 */
public interface UserService {

    User findByAccount(String account);

}