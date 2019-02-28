package com.liyulin.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "product")
public class ProductEntity {

	/** 商品id */
	@Id
	private long id;

	/** 商品名称 */
	@Field(type = FieldType.Text)
	private String name;

	/** 商品价格（单位：万分之一元） */
	@Field(type = FieldType.Long, index = false)
	private long price;

	public static final class Column {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String PRICE = "price";
	}

}