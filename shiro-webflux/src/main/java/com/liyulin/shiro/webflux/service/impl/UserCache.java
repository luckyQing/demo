package com.liyulin.shiro.webflux.service.impl;

import com.liyulin.shiro.webflux.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author collin
 * @date 2021-06-09
 */
public class UserCache {

    private static final Map<String, User> USER_CACHE = new HashMap<>();

    public static Map<String, User> getUserCache() {
        return USER_CACHE;
    }

}