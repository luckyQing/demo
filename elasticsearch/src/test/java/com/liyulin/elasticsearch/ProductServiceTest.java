package com.liyulin.elasticsearch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liyulin.elasticsearch.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	public void insert() {
		productService.insert();
	}

	@Test
	public void batchInsert() {
		productService.batchInsert();
	}

	@Test
	public void delete() {
		productService.delete();
	}

	@Test
	public void update() {
		productService.update();
	}

	@Test
	public void count() {
		long count = productService.count();
		log.info("count={}", count);
	}

	@Test
	public void query() {
		productService.query();
	}
}