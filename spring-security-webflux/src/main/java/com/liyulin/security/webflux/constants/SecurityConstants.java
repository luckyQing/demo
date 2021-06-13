package com.liyulin.security.webflux.constants;

import org.springframework.http.HttpHeaders;

/**
 * 常量
 *
 * @author collin
 * @date 2021-06-16
 */
public class SecurityConstants {

    /**
     * http header token
     */
    public static final String TOKEN = HttpHeaders.AUTHORIZATION;

    /**
     * 登录接口url前缀
     */
    public static final String LOGIN_URL = "/auth/login";

}