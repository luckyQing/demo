package com.liyulin.spring5.mvcAnnotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.spring5.dto.req.UserReqBody;
import com.liyulin.spring5.dto.resp.UserRespBody;
import com.liyulin.spring5.mvcAnnotation.service.MvcAnnotationUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Reactor模式1：mvc注解形式
 *
 * @author liyulin
 * @date 2019年3月10日下午1:26:54
 */
@RestController
@RequestMapping("mvc/user")
public class MvcAnnotationController {
	
	@Autowired
	private MvcAnnotationUserService userService;

	@PostMapping
	public Mono<Boolean> create(@RequestBody UserReqBody reqBody) {
		return userService.create(reqBody);
	}

	@GetMapping
	public Flux<UserRespBody> users() {
		return userService.users();
	}
	
	@GetMapping("{id}")
	public Mono<UserRespBody> user(@PathVariable long id) {
		return userService.user(id);
	}
	
}