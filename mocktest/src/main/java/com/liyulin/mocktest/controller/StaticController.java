package com.liyulin.mocktest.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.mocktest.service.StaticService;

@RestController
@Validated
@RequestMapping("static")
public class StaticController {
	
	@Autowired
	private StaticService staticService;

	@PostMapping("isBlank")
	public Boolean isBlank(@RequestBody @NotBlank String str) {
		return staticService.isBlank(str);
	}
	
}