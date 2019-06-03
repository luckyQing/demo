package com.liyulin.shading.jdbc.demo.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisSqlLogInterceptorAutoConfigure {

	@Bean
	public MybatisSqlLogInterceptor mybatisSqlLogInterceptor() {
		return new MybatisSqlLogInterceptor();
	}
	
}