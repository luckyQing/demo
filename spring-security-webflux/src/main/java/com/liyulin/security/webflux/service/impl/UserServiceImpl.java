package com.liyulin.security.webflux.service.impl;

import com.liyulin.security.webflux.biz.UserBiz;
import com.liyulin.security.webflux.entity.User;
import com.liyulin.security.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBiz userBiz;

    @Override
    public User find(Long mobile) {
        return userBiz.find(mobile);
    }

}