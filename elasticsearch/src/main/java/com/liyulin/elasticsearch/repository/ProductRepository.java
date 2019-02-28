package com.liyulin.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.liyulin.elasticsearch.entity.ProductEntity;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductEntity, Long> {

}