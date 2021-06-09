package com.liyulin.shiro.webflux.service.impl;

import com.liyulin.shiro.webflux.entity.User;

/**
 * @author collin
 * @date 2021-06-09
 */
public class UserContext {

    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    public static User getUser() {
        return CURRENT_USER.get();
    }

    public static void setUser(User user){
        CURRENT_USER.set(user);
    }

}
