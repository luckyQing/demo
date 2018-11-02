package com.liyulin.httpxml.xml.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品xml
 *
 * @author liyulin
 * @date 2018年11月2日下午11:04:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "PRODUCT")
@ApiModel(description = "商品 xml格式入参")
public class XmlProductDto {

	@ApiModelProperty("商品id")
	@JacksonXmlProperty(localName = "ID")
	private long id;

	@ApiModelProperty("商品名称")
	@JacksonXmlProperty(localName = "NAME")
	private String name;

	@ApiModelProperty("商品价格")
	@JacksonXmlProperty(localName = "PRICE")
	private long price;

}
