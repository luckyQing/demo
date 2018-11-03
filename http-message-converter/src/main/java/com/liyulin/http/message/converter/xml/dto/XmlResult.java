package com.liyulin.http.message.converter.xml.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.liyulin.http.message.converter.enums.ReturnCodeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * xml格式返回参数
 *
 * @author liyulin
 * @date 2018年11月3日上午8:24:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "RESULT")
public class XmlResult<T> {

	/** 返回状态码 */
	@JacksonXmlProperty(localName = "CODE")
	private int code;

	/** 返回消息 */
	@JacksonXmlProperty(localName = "MSG")
	private String msg;

	/** 返回数据 */
	@JacksonXmlProperty(localName = "DATA")
	private T data;

	public XmlResult(ReturnCodeEnum returnCodeEnum) {
		this.code = returnCodeEnum.getCode();
		this.msg = returnCodeEnum.getMsg();
	}
	
	public XmlResult(ReturnCodeEnum returnCodeEnum, T data) {
		this.code = returnCodeEnum.getCode();
		this.msg = returnCodeEnum.getMsg();
		this.data = data;
	}

}
