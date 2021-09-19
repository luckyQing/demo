package com.liyulin.jpa.test;

import com.liyulin.jpa.entity.ProductInfoEntity;
import com.liyulin.jpa.service.ProductInfoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AppTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    void testCreate() {
        productInfoService.create();
    }

    @Test
    void testUpdate() {
        ProductInfoEntity entity = productInfoService.create();

        productInfoService.update(entity.getId(), "xxxxx");
    }

}