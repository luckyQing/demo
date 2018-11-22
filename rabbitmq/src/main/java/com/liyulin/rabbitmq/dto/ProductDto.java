package com.liyulin.rabbitmq.dto;

import lombok.Data;

/**
 * 商品信息dto
 *
 * @author liyulin
 * @date 2018年11月6日下午3:14:00
 */
@Data
public class ProductDto {

	private long id;
	private String name;
	private long price;

}
