package com.liyulin.ratelimiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sentinel")
public class SentinelController {

	@GetMapping
	public String get() {
		return "success";
	}
	
}