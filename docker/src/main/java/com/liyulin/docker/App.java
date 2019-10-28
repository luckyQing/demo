package com.liyulin.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {
	
	@GetMapping
	public String index() {
		return "docker ok!";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}