package com.liyulin.hibernate.validator.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.hibernate.validator.entity.Req;
import com.liyulin.hibernate.validator.entity.Result;
import com.liyulin.hibernate.validator.entity.StudentVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@Api(tags = "自定义泛型参数校验接口")
public class ReqTController {

	@ApiOperation("泛型参数校验")
	@PostMapping("test1")
	public Result test1(@RequestBody @Valid Req<@NotNull(message = "参数不能为空") StudentVO> req) {
		return new Result();
	}

	@ApiOperation("非泛型参数校验")
	@PostMapping("test2")
	public Result test2(@RequestBody @Valid StudentVO studentVO) {
		return new Result();
	}

}