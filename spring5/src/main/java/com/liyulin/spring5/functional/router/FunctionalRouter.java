package com.liyulin.spring5.functional.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

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
	
	@Bean
	public RouterFunction<?> routerFunction() {
		return RouterFunctions.route(GET("/fun/user").and(accept(MediaType.APPLICATION_JSON)), userService::users);
//		return route(
//				GET("/api/city").and(Accept(MediaType.APPLICATION_JSON)), cityService::findAllCity).and(
//						route(
//
//								GET("/api/user/{id}").and(accept(MediaType.APPLICATION_JSON)),
//								cityService::findCityById)
//
//		);
	}

}