package com.liyulin.http.message.converter.json.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.http.message.converter.enums.ReturnCodeEnum;
import com.liyulin.http.message.converter.json.dto.JsonProductDto;
import com.liyulin.http.message.converter.json.dto.JsonResult;

@RestController
public class JsonProductController {

	@PostMapping("/json")
	public JsonResult<JsonProductDto> json(@RequestBody JsonProductDto jsonProductDto) {
		return new JsonResult<>(ReturnCodeEnum.SUCCESS, jsonProductDto);
	}

}
