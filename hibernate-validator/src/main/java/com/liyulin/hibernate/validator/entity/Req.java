package com.liyulin.hibernate.validator.entity;

import javax.validation.Valid;

import lombok.Data;

@Data
public class Req<T> {
	private Long id;

	@Valid
	private T body;

}
