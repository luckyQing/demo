package com.liyulin.httpxml.xml.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回参数")
@JacksonXmlRootElement(localName = "RESULT")
public class XmlResultT<T> {

	@ApiModelProperty("返回状态码")
	@JacksonXmlProperty(localName = "CODE")
	private String code;

	@ApiModelProperty("返回消息")
	@JacksonXmlProperty(localName = "MSG")
	private String msg;

	@ApiModelProperty("返回数据")
	@JacksonXmlProperty(localName = "DATA")
	private T data;

}
