package com.demo.collin.rocketmq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.collin.rocketmq.entity.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author collin
 * @since 2025-01-15
 */
@Getter
@Setter
@TableName("t_product_info")
public class ProductInfoEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @TableField("f_name")
    private String name;

    /**
     * 销售价格（单位：万分之一元）
     */
    @TableField("f_sell_price")
    private Long sellPrice;

    /**
     * 库存
     */
    @TableField("f_stock")
    private Long stock;
}
