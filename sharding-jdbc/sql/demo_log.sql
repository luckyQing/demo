CREATE DATABASE `sharding_log_db_2022` /*!40100 COLLATE 'utf8mb4_bin' */;
CREATE DATABASE `sharding_log_db_2023` /*!40100 COLLATE 'utf8mb4_bin' */;
CREATE DATABASE `sharding_log_db_2024` /*!40100 COLLATE 'utf8mb4_bin' */;
CREATE DATABASE `sharding_log_db_2025` /*!40100 COLLATE 'utf8mb4_bin' */;

USE `sharding_log_db_2022`;

CREATE TABLE `t_thrid_log_01`
(
    `f_id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `f_vendor`         VARCHAR(64)  NOT NULL COMMENT '供用商',
    `f_url`            VARCHAR(256) NOT NULL COMMENT '接口url',
    `f_req_start_time` DATETIME     NOT NULL COMMENT '请求开始时间',
    `f_req_end_time`   DATETIME     NOT NULL COMMENT '请求截止时间',
    `f_cost_time`      INT(11) UNSIGNED NOT NULL COMMENT '请求耗时（毫秒）',
    `f_req_params`     VARCHAR(512) NULL DEFAULT NULL COMMENT '请求的参数信息',
    `f_response`       TEXT NULL COMMENT '响应的数据',
    `f_exception_msg`  TEXT NULL COMMENT '异常堆栈信息',
    `f_create_time`    DATETIME     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`f_id`) USING BTREE,
    INDEX              `idx_f_vendor_f_url` (`f_vendor`, `f_url`),
    INDEX              `idx_f_req_start_time_f_req_end_time` (`f_req_start_time`, `f_req_end_time`)
) COMMENT='接口日志记录' COLLATE='utf8mb4_bin' ENGINE=InnoDB;

CREATE TABLE t_thrid_log_02 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_03 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_04 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_05 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_06 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_07 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_08 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_09 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_10 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_11 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_12 like sharding_log_db_2022.t_thrid_log_01;


USE `sharding_log_db_2023`;
CREATE TABLE t_thrid_log_01 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_02 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_03 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_04 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_05 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_06 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_07 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_08 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_09 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_10 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_11 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_12 like sharding_log_db_2022.t_thrid_log_01;

USE `sharding_log_db_2024`;
CREATE TABLE t_thrid_log_01 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_02 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_03 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_04 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_05 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_06 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_07 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_08 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_09 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_10 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_11 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_12 like sharding_log_db_2022.t_thrid_log_01;

USE `sharding_log_db_2025`;
CREATE TABLE t_thrid_log_01 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_02 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_03 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_04 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_05 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_06 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_07 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_08 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_09 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_10 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_11 like sharding_log_db_2022.t_thrid_log_01;
CREATE TABLE t_thrid_log_12 like sharding_log_db_2022.t_thrid_log_01;