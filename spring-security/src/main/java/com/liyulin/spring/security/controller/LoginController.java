package com.liyulin.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("login")
public class LoginController {

	@GetMapping
	public String pageLogin() {
		return "login";
	}
	
	@GetMapping("error")
	public String pageLoginError() {
		return "loginError";
	}
	
	@PostMapping
	@ResponseBody
	public String check() {
		return "ok";
	}
	
}
