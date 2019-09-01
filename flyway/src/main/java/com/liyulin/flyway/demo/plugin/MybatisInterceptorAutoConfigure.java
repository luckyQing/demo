package com.liyulin.flyway.demo.plugin;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageInterceptor;

@Configuration
public class MybatisInterceptorAutoConfigure {

	@Bean
	public MybatisSqlLogInterceptor mybatisSqlLogInterceptor() {
		return new MybatisSqlLogInterceptor();
	}
	
	@Bean
	public PageInterceptor pageInterceptor() {
		PageInterceptor pageHelper = new PageInterceptor();
		Properties p = new Properties();
		// 分页合理化参数
		p.setProperty("reasonable", "true");
		p.setProperty("supportMethodsArguments", "true");
		p.setProperty("params", "count=countSql");
		pageHelper.setProperties(p);
		return pageHelper;
	}
	
}