package com.liyulin.hibernate.validator.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.liyulin.hibernate.validator.entity.QryTransResultReqVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.hibernate.validator.entity.ProductVO;
import com.liyulin.hibernate.validator.entity.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "实体对象校验接口")
public class EntityParamController {

	@ApiOperation("实体对象校验")
	@PostMapping("entity")
	public Result entity(@Valid ProductVO productVO) {
		return new Result();
	}

	@GetMapping("test")
	public Result qryTransResults(@RequestBody @NotNull @Valid QryTransResultReqVO reqVO) {
		return new Result();
	}

}