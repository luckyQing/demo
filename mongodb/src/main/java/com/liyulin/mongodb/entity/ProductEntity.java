package com.liyulin.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品信息
 *
 * @author liyulin
 * @date 2019年2月27日下午11:03:48
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "product") // 标明由mongo来维护该表
public class ProductEntity {

	/** 商品id */
	@Id // 主键，不可重复，自带索引
	private long id;

	/** 商品名称 */
	@Indexed // 声明该字段需要加索引
	private String name;

	/** 商品价格（单位：万分之一元） */
	@Field("price") // 代表一个字段，可以不加，不加的话默认以参数名为列名
	private long price;

	public static final class Column {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String PRICE = "price";
	}

}