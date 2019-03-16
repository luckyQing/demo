package com.liyulin.spring5.functional.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.liyulin.spring5.functional.service.FunctionalUserService;

/**
 * Reactor模式2：函数路由形式
 *
 * @author liyulin
 * @date 2019年3月10日下午1:27:24
 */
@Configuration
public class FunctionalRouter {

	@Autowired
	private FunctionalUserService userService;

//	@Bean
//	public RouterFunction<?> routerFunction() {
//		return nest(path("/fun/user").and(accept(MediaType.APPLICATION_JSON)), 
//				route(GET("/{id}"), userService::user)
//				.and(route(GET(""), userService::users))
//				.and(route(POST(""), userService::create)));
//	}
	
	@Bean
	public RouterFunction<?> routerFunction() {
		return nest(accept(MediaType.APPLICATION_JSON), 
				route(GET("/fun/user/{id}"), userService::user)
				.and(route(GET("/fun/user"), userService::users))
				.and(route(POST("/fun/user"), userService::create)));
	}

}