package com.liyulin.http.message.converter.json.dto;

import com.liyulin.http.message.converter.enums.ReturnCodeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回参数")
public class JsonResult<T> {

	@ApiModelProperty("返回状态码")
	private int code;

	@ApiModelProperty("返回消息")
	private String msg;

	@ApiModelProperty("返回数据")
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
