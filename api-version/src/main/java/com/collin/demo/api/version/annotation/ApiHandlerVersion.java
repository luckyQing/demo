package com.collin.demo.api.version.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface ApiHandlerVersion {

    /**
     * api handler路由前缀
     *
     * @return
     */
    String routeKeyPrefix();

    /**
     * api的版本号（默认为1，当为api handler后缀）
     *
     * @return
     */
    int version() default 1;

    String method() default "handler";

}