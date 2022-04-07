package com.liyulin.jpa.service;

import com.liyulin.jpa.entity.ProductInfoEntity;
import com.liyulin.jpa.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    public ProductInfoEntity create() {
        ProductInfoEntity entity = new ProductInfoEntity();
        entity.setName(UUID.randomUUID().toString());
        entity.setSellPrice(100L);
        entity.setStock(100L);
        entity.setInsertTime(new Date());
        entity.setInsertUser(1L);
        entity.setDelState((byte) 0);

        return productInfoRepository.save(entity);
    }

    public void update(Long id, String name){
        ProductInfoEntity entity = new ProductInfoEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setUpdTime(new Date());
        productInfoRepository.save(entity);
    }

}