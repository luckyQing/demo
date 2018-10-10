package com.liyulin.hibernate.validator.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.hibernate.validator.entity.ProductVO;
import com.liyulin.hibernate.validator.entity.Result;
import com.liyulin.hibernate.validator.entity.ValidatorGroup;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "分组校验接口")
public class GroupController {

	@ApiOperation("分组校验")
	@GetMapping("group1")
	public Result group1(@Validated(ValidatorGroup.Insert.class) ProductVO productVO) {
		return new Result();
	}

	@ApiOperation("非分组校验")
	@GetMapping("group2")
	public Result group2(@Validated ProductVO productVO) {
		return new Result();
	}

}
