package com.liyulin.httpxml.json.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "商品 json格式入参")
public class JsonProductDto {

	@ApiModelProperty("商品id")
	private long id;

	@ApiModelProperty("商品名称")
	private String name;

	@ApiModelProperty("商品价格")
	private long price;

}
