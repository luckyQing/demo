package com.liyulin.gradle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.gradle.pojo.vo.TestResp;

@RestController
public class TestController {

	@GetMapping("test")
	public TestResp test() {
		return TestResp.builder().id(1L).name("test").price(100L).build();
	}

}