package com.liyulin.hibernate.validator.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyulin.hibernate.validator.entity.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@Api(tags = "普通参数校验接口")
public class ParamController {

	@ApiOperation("普通参数校验")
	@GetMapping("validated")
	public Result test2(@NotBlank(message = "姓名不能为空") 
						@Length(max = 10, message = "姓名最大长度为10") 
						String name, 
						@NotNull(message = "年龄不能为空")
						@Min(value = 1, message = "年龄最小值为1")
						@Max(value = 100, message = "年龄最大值为100")
						Integer age) {
		return new Result();
	}
	
}
