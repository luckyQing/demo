package com.liyulin.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.liyulin.elasticsearch.entity.ProductEntity;
import com.liyulin.elasticsearch.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * 增加
	 */
	public void insert() {
		ProductEntity productEntity = ProductEntity.builder()
				.id(123456L)
				.name("手机")
				.price(12800000L)
				.build();
		productRepository.save(productEntity);
	}
	
	/**
	 * 批量增加
	 * 
	 * @return
	 */
	public boolean batchInsert() {
		List<ProductEntity> entities = new ArrayList<>();
		for(int i=0; i<100; i++) {
			ProductEntity productEntity = ProductEntity.builder()
					.id(i)
					.name("手机"+i)
					.price(i)
					.build();
			
			entities.add(productEntity);
		}
		
		productRepository.saveAll(entities);
		return true;
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		productRepository.deleteById(1L);
	}
	
	/**
	 * 更新
	 */
	public void update() {
		ProductEntity productEntity = ProductEntity.builder()
				.id(10L)
				.name("手机")
				.price(12800000L)
				.build();
		productRepository.save(productEntity);
	}
	
	public long count() {
		return productRepository.count();
	}
	
	/**
	 * 分页查询
	 */
	public void query() {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				// 查询条件
				.withQuery(QueryBuilders.matchQuery(ProductEntity.Column.NAME, "手机"))
				// 分页
				.withPageable(PageRequest.of(1, 10))
				// 倒序
				.withSort(SortBuilders.fieldSort(ProductEntity.Column.ID).order(SortOrder.DESC))
				.build();
		Page<ProductEntity> productEntityPage = productRepository.search(searchQuery);
		int total = productEntityPage.getTotalPages();
		List<ProductEntity> productList = productEntityPage.getContent();
		
		log.info("total={}; productList={}", total, JSON.toJSONString(productList));
	}
	
}