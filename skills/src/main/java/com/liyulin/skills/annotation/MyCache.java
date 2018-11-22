package com.liyulin.skills.annotation;

public @interface MyCache {

	public String key();
	
	public long millSeconds() default 1000L;
	
}
