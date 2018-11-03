package com.liyulin.http.message.converter.json.dto;

import com.liyulin.http.message.converter.enums.ReturnCodeEnum;

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
public class JsonResult<T> {

	private int code;

	private String msg;

	private T data;

	public JsonResult(ReturnCodeEnum returnCodeEnum) {
		this.code = returnCodeEnum.getCode();
		this.msg = returnCodeEnum.getMsg();
	}

	public JsonResult(ReturnCodeEnum returnCodeEnum, T data) {
		this.code = returnCodeEnum.getCode();
		this.msg = returnCodeEnum.getMsg();
		this.data = data;
	}
	
}
