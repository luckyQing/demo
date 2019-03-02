package com.liyulin.mongodb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liyulin.mongodb.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
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
	public void query() {
		productService.query();
	}

	@Test
	public void update() {
		productService.update();
	}

	@Test
	public void page() {
		productService.page();
	}
	
}