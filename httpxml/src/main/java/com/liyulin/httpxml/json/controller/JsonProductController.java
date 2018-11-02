package com.liyulin.httpxml.json.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.httpxml.json.dto.JsonProductDto;
import com.liyulin.httpxml.json.dto.JsonResultT;

@RestController
public class JsonProductController {

	@PostMapping(value = "/json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonResultT<JsonProductDto> test(@RequestBody JsonProductDto jsonProductDto) {
		return new JsonResultT<>("0000", "成功", jsonProductDto);
	}

}
