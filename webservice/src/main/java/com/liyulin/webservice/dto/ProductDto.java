package com.liyulin.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品 json格式入参
 *
 * @author liyulin
 * @date 2018年11月3日下午12:22:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private long id;

	private String name;

	private long price;

}
