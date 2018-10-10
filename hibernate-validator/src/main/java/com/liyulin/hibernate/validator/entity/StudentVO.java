package com.liyulin.hibernate.validator.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class StudentVO {

	@NotBlank(message = "姓名不能为空")
	@Length(max = 10, message = "姓名最大长度为10")
	private String name;

	@NotNull(message = "年龄不能为空")
	@Min(value = 1, message = "年龄最小值为1")
	@Max(value = 100, message = "年龄最大值为100")
	private int age;

}
