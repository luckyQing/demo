package com.liyulin.http.message.converter.xml.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品 xml格式入参
 *
 * @author liyulin
 * @date 2018年11月2日下午11:04:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "PRODUCT")
public class XmlProductDto {

	/** 商品id */
	@JacksonXmlProperty(localName = "ID")
	private long id;

	/** 商品名称 */
	@JacksonXmlProperty(localName = "NAME")
	private String name;

	/** 商品价格 */
	@JacksonXmlProperty(localName = "PRICE")
	private long price;

}
