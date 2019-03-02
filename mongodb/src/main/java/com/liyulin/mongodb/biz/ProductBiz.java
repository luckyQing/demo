package com.liyulin.mongodb.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.liyulin.mongodb.entity.ProductEntity;
import com.liyulin.mongodb.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductBiz {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 插入
	 */
	public void insert(long id, String name, long price) {
		ProductEntity entity = new ProductEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setPrice(price);

		productRepository.insert(entity);
	}
	
	/**
	 * 批量插入
	 */
	public void batchInsert() {
		int count = 200;
		List<ProductEntity> entities = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			ProductEntity entity = new ProductEntity();
			entity.setId(i);
			entity.setName("手机华为P" + i);
			entity.setPrice(i);

			entities.add(entity);
		}

		productRepository.insert(entities);
	}

	/**
	 * 删除
	 */
	public void delete() {
		productRepository.deleteById(1L);
	}

	/**
	 * 查询
	 */
	public void query() {
		Optional<ProductEntity> entity = productRepository.findById(1L);

		log.info("entity100={}", entity.get());
	}
	
	/**
	 * 更新
	 */
	public void update() {
		ProductEntity entity = new ProductEntity();
		entity.setId(1);
		entity.setName("手机华为PX");
		entity.setPrice(20);

		productRepository.save(entity);
	}
	
	/**
	 * 分页
	 */
	public void page() {
		// 查询条件
		//完全匹配
		//Pattern fullPattern = Pattern.compile("^手机$", Pattern.CASE_INSENSITIVE);
		//右匹配
		//Pattern rightPattern = Pattern.compile("^.*手机$", Pattern.CASE_INSENSITIVE);
		// 不区分大小写的模糊左匹配
		//Pattern leftPattern = Pattern.compile("^手机.*$", Pattern.CASE_INSENSITIVE);
		//模糊匹配
		Pattern pattern = Pattern.compile("^.*手机.*$");
		Criteria criteria = Criteria.where(ProductEntity.Column.NAME).regex(pattern);
		Query query = new Query();
		query.addCriteria(criteria);
		long count = mongoTemplate.count(query, ProductEntity.class);
		
		// 排序
		Sort sort = new Sort(Sort.Direction.DESC, ProductEntity.Column.ID)
				.and(new Sort(Sort.Direction.ASC, ProductEntity.Column.PRICE));
		query.with(sort);
		// 分页
		query.with(PageRequest.of(0, 30));
		List<ProductEntity> entities = mongoTemplate.find(query, ProductEntity.class);
		
		log.info("count={}", count);
		log.info("entities={}", entities);
	}
	
}