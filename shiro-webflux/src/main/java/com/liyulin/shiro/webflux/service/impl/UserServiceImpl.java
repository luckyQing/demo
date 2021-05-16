package com.liyulin.shiro.webflux.service.impl;

import com.liyulin.shiro.webflux.biz.UserBiz;
import com.liyulin.shiro.webflux.entity.User;
import com.liyulin.shiro.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBiz userBiz;

    @Override
    public User findByAccount(String account) {
        return userBiz.findByAccount(account);
    }

}