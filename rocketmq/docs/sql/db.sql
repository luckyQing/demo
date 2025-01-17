create database rocketmq_transaction_db;
use rocketmq_transaction_db;

CREATE TABLE rocketmq_transaction_db.t_mq_transaction_log (
    `f_id` bigint(64) NOT NULL COMMENT '主键id',
    `f_transaction_no` varchar(64) NOT NULL COMMENT '事务号',
    `f_code` varchar(128) NOT NULL COMMENT '编码',
    `f_log` text COMMENT '日志',
    `f_sys_insert_time` datetime NOT NULL COMMENT '创建时间',
    `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
    `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
    `f_sys_insert_user` bigint(20) unsigned DEFAULT NULL COMMENT '新增者',
    `f_sys_upd_user` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
    `f_sys_del_user` bigint(20) unsigned DEFAULT NULL COMMENT '删除者',
    `f_sys_del_state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除状态=={1:正常, 2:已删除}',
    PRIMARY KEY (`f_id`) USING BTREE,
    UNIQUE KEY `udx_no_code` (`f_transaction_no`,`f_code`),
    KEY `idx_f_sys_insert_time` (`f_sys_insert_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE rocketmq_transaction_db.t_order_bill (
    `f_id` bigint(20) unsigned NOT NULL,
    `f_order_no` varchar(32) NOT NULL COMMENT '订单号',
    `f_amount` bigint(20) unsigned NOT NULL COMMENT '订单金额总金额',
    `f_status` tinyint(2) unsigned NOT NULL COMMENT '订单状态（1：待扣减库存；2：扣减库存失败；3：抵扣优惠券失败；4：待付款；5：已取消；6：待发货；7：待收货；8：待评价，9：已完成）',
    `f_pay_state` tinyint(1) unsigned NOT NULL COMMENT '支付状态（1：待支付；2：支付成功；3：支付失败；4：待退款；5：退款成功；6：退款失败）',
    `f_buyer` bigint(20) unsigned NOT NULL COMMENT '购买人id（demo_user库t_user_info表f_id）',
    `f_sys_insert_time` datetime NOT NULL COMMENT '创建时间',
    `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
    `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
    `f_sys_insert_user` bigint(20) unsigned DEFAULT NULL COMMENT '新增者',
    `f_sys_upd_user` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
    `f_sys_del_user` bigint(20) unsigned DEFAULT NULL COMMENT '删除者',
    `f_sys_del_state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除状态=={1:正常, 2:已删除}',
    PRIMARY KEY (`f_id`),
    KEY `uk_order_no` (`f_order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息';

CREATE TABLE rocketmq_transaction_db.t_product_info (
    `f_id` bigint(20) unsigned NOT NULL,
    `f_name` varchar(100) NOT NULL COMMENT '商品名称',
    `f_sell_price` bigint(20) unsigned NOT NULL COMMENT '销售价格（单位：万分之一元）',
    `f_stock` bigint(20) unsigned NOT NULL COMMENT '库存',
    `f_sys_insert_time` datetime NOT NULL COMMENT '创建时间',
    `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
    `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
    `f_sys_insert_user` bigint(20) unsigned DEFAULT NULL COMMENT '新增者',
    `f_sys_upd_user` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
    `f_sys_del_user` bigint(20) unsigned DEFAULT NULL COMMENT '删除者',
    `f_sys_del_state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除状态=={1:正常, 2:已删除}',
    PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息';


INSERT INTO rocketmq_transaction_db.`t_product_info`(`f_id`, `f_name`, `f_sell_price`, `f_stock`, `f_sys_insert_time`, `f_sys_upd_time`, `f_sys_del_time`, `f_sys_insert_user`, `f_sys_upd_user`, `f_sys_del_user`, `f_sys_del_state`) VALUES (1, 'vivo mobile', 200, 10000, '2025-01-16 17:16:50', NULL, NULL, 1, NULL, NULL, 0);