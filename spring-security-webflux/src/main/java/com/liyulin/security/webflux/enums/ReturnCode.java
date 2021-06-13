package com.liyulin.security.webflux.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author collin
 * @date 2021-06-17
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReturnCode {
    /**
     * 认证失败
     */
    SUCCESS(0, "成功"),
    /**
     * 认证失败
     */
    AUTHENTICATION_FAIL(10060001, "认证失败"),
    /**
     * 无权限访问
     */
    AUTHORIZATION_DENIED(10060002, "无权限访问");

    private int code;
    private String msg;

}