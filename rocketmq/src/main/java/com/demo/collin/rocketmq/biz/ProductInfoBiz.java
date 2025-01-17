package com.demo.collin.rocketmq.biz;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.collin.rocketmq.dao.ProductInfoDao;
import com.demo.collin.rocketmq.entity.ProductInfoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author collin
 * @since 2025-01-15
 */
@Service
public class ProductInfoBiz extends ServiceImpl<ProductInfoDao, ProductInfoEntity> {

    public boolean updateStock(Long skuId, Long count) {
        ProductInfoEntity oldEntity = getById(skuId);

        ProductInfoEntity entity = new ProductInfoEntity();
        entity.setId(skuId);
        entity.setStock(oldEntity.getStock() - count);
        entity.setSysUpdTime(LocalDateTime.now());
        return updateById(entity);
    }

}