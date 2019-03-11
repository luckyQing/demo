package com.liyulin.spring5.functional.autoconfigure;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局错误处理
 *
 * @author liyulin
 * @date 2019年3月10日下午2:30:50
 */
@Component
@Slf4j
public class GlobalErrorHandler extends DefaultErrorWebExceptionHandler {

	public GlobalErrorHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ErrorProperties errorProperties, ApplicationContext applicationContext) {
		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
	}
	
	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		RouterFunction<ServerResponse> routerFunction = super.getRoutingFunction(errorAttributes);
		// TODO:处理逻辑
		log.info("errorAttributes==>{}", JSON.toJSONString(errorAttributes));
		
		return routerFunction;
	}

}