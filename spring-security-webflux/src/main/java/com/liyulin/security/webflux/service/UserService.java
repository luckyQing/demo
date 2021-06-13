package com.liyulin.security.webflux.service;

import com.liyulin.security.webflux.entity.User;

/**
 * @author collin
 * @date 2021-05-24
 */
public interface UserService {

    User find(Long mobile);

}