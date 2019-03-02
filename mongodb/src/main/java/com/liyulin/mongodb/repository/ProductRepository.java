package com.liyulin.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.liyulin.mongodb.entity.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, Long> {

}