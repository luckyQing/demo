package com.liyulin.skills.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@Inherited
@Documented
public @interface MyCache {

	public String key();

	public long millSeconds() default 1000L;

}