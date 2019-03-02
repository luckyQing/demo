package com.liyulin.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyulin.mongodb.biz.ProductBiz;

@Service
public class ProductService {

	@Autowired
	private ProductBiz productBiz;

	/**
	 * 插入
	 */
	public void insert() {
		productBiz.insert(1000000L, "华为手机P200", 2000000L);
	}
	
	/**
	 * 批量插入
	 */
	public void batchInsert() {
		productBiz.batchInsert();
	}

	/**
	 * 删除
	 */
	public void delete() {
		productBiz.delete();
	}

	/**
	 * 查询
	 */
	public void query() {
		productBiz.query();
	}

	/**
	 * 更新
	 */
	public void update() {
		productBiz.update();
	}

	/**
	 * 分页
	 */
	public void page() {
		productBiz.page();
	}

}