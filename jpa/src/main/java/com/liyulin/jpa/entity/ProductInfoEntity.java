package com.liyulin.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息
 *
 * @author liyulin
 * @date 2021-03-14
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_product_info")
public class ProductInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Long id;

    /**
     * 商品名称
     */
    @Column(name = "f_name")
    private String name;

    /**
     * 销售价格（单位：万分之一元）
     */
    @Column(name = "f_sell_price")
    private Long sellPrice;

    /**
     * 库存
     */
    @Column(name = "f_stock")
    private Long stock;

    /**
     * 创建时间
     */
    @Column(name = "f_sys_insert_time")
    private Date insertTime;

    /**
     * 更新时间
     */
    @Column(name = "f_sys_upd_time")
    private Date updTime;

    /**
     * 删除时间
     */
    @Column(name = "f_sys_del_time")
    private Date delTime;

    /**
     * 新增者
     */
    @Column(name = "f_sys_insert_user")
    private Long insertUser;

    /**
     * 更新者
     */
    @Column(name = "f_sys_upd_user")
    private Long updUser;

    /**
     * 删除者
     */
    @Column(name = "f_sys_del_user")
    private Long delUser;

    /**
     * 删除状态=={'1':'正常','2':'已删除'}
     */
    @Column(name = "f_sys_del_state")
    private Byte delState;

}