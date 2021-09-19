CREATE TABLE `t_product_info` (
  `f_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
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
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品信息';