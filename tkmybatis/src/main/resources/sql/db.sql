SET MODE=MySQL;

CREATE TABLE IF NOT EXISTS `t_test` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(50) DEFAULT NULL,
  `f_sys_insert_time` datetime NOT NULL COMMENT '创建时间',
  `f_sys_upd_time` datetime DEFAULT NULL COMMENT '更新时间',
  `f_sys_del_time` datetime DEFAULT NULL COMMENT '删除时间',
  `f_sys_insert_user` bigint(20) unsigned DEFAULT NULL COMMENT '新增者',
  `f_sys_upd_user` bigint(20) unsigned DEFAULT NULL COMMENT '更新者',
  `f_sys_del_user` bigint(20) unsigned DEFAULT NULL COMMENT '删除者',
  `f_sys_del_state` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '删除状态=={1:正常, 2:已删除}',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `trade_req` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '雪花id',
	`user_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
	`user_id_short` BIGINT(20) UNSIGNED NULL DEFAULT NULL COMMENT '客户短号',
	`fund_account` VARCHAR(32) NOT NULL COMMENT '用户对应的恒生账户',
	`account_business_type` SMALLINT(6) NOT NULL DEFAULT '100' COMMENT '账号业务类型：100-正股，101-日内融账户，102-DA，103-MA公共，104-MA费用，105-跟投，300-期权账户，400-沽空，501-基金，502-现金加',
	`partner_trade_no` VARCHAR(50) NOT NULL COMMENT '业务系统当次业务请求幂等标识',
	`trade_type` INT(11) NOT NULL COMMENT '交易业务类型',
	`trade_sub_type` VARCHAR(50) NULL DEFAULT NULL COMMENT '交易业务子类型',
	`business_id` BIGINT(20) NULL DEFAULT NULL COMMENT '业务相关的id',
	`money_type` VARCHAR(4) NOT NULL COMMENT '币种类型，CNY人民币，USD美元，HKD港币',
	`exchange_type` VARCHAR(4) NULL DEFAULT NULL COMMENT '市场类型，HK港股，US美股，HGT沪港通，SGT深港通',
	`trade_status` TINYINT(4) UNSIGNED NOT NULL DEFAULT '10' COMMENT '交易操作状态10待处理  20处理中 21处理中,明细有失败 30成功 40失败 41失败待确认',
	`tran_date` DATE NULL DEFAULT NULL COMMENT '该操作的交易日时间',
	`business_time` DATETIME NULL DEFAULT NULL COMMENT '该流水业务发生的时间',
	`req_url` VARCHAR(100) NULL DEFAULT NULL COMMENT '请求url',
	`req_data` MEDIUMTEXT NULL COMMENT '请求参数',
	`resp_data` VARCHAR(4000) NULL DEFAULT NULL COMMENT '响应数据',
	`remark` VARCHAR(128) NULL DEFAULT NULL COMMENT '备注',
	`version` INT(10) UNSIGNED NOT NULL DEFAULT '1' COMMENT '乐观锁版本号',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
)COMMENT='请求表' ENGINE=InnoDB;