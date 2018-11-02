package com.liyulin.httpxml.json.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回参数")
public class JsonResultT<T> {

	@ApiModelProperty("返回状态码")
	private String code;

	@ApiModelProperty("返回消息")
	private String msg;

	@ApiModelProperty("返回数据")
	private T data;

}
