package com.liyulin.skills.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Collections常用方法
 *
 * @author liyulin
 * @date 2018年10月7日下午10:48:08
 */
@Slf4j
public class CollectionsDemo {

	/**
	 * 自定义排序
	 */
	@Test
	public void testComparator() {
		// 造数据（倒序）
		List<Product> productList = new ArrayList<>();
		for (long i = 10; i > 0; i--) {
			productList.add(new Product(i, i));
		}
		// 排序（升序）
		Collections.sort(productList, new Comparator<Product>() {

			@Override
			public int compare(Product o1, Product o2) {
				return (int) (o1.getPrice() - o2.getPrice());
			}

		});

		// 打印结果
		productList.forEach(item -> {
			log.info(item.toString());
		});
	}

	@Data
	@AllArgsConstructor
	class Product {
		/** 商品id */
		private long id;
		/** 商品价格 */
		private long price;
	}

}
