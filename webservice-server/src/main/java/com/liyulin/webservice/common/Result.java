package com.liyulin.webservice.common;

import com.liyulin.webservice.enums.ReturnCodeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json格式返回参数
 *
 * @author liyulin
 * @date 2018年11月3日下午12:21:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

	private int code;

	private String msg;

	private T data;

	public Result(ReturnCodeEnum returnCodeEnum) {
		this.code = returnCodeEnum.getCode();
		this.msg = returnCodeEnum.getMsg();
	}

	public Result(ReturnCodeEnum returnCodeEnum, T data) {
		this.code = returnCodeEnum.getCode();
		this.msg = returnCodeEnum.getMsg();
		this.data = data;
	}
	
}
