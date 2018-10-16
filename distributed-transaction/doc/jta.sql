-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.17 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 jta_demo_db1 的数据库结构
CREATE DATABASE IF NOT EXISTS `jta_demo_db1` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jta_demo_db1`;


-- 导出  表 jta_demo_db1.t_student 结构
CREATE TABLE IF NOT EXISTS `t_student` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(20) NOT NULL COMMENT '姓名',
  `f_sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别==>{"0":"未知","1":"男";"2":"女"}',
  `f_age` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '年龄（为空，则值为-1）',
  `f_grade` varchar(20) DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='学生信息表';

-- 正在导出表  jta_demo_db1.t_student 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` (`f_id`, `f_name`, `f_sex`, `f_age`, `f_grade`) VALUES
	(1, '张三', 1, 10, '六年级');
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;


-- 导出 jta_demo_db2 的数据库结构
CREATE DATABASE IF NOT EXISTS `jta_demo_db2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jta_demo_db2`;


-- 导出  表 jta_demo_db2.t_teacher 结构
CREATE TABLE IF NOT EXISTS `t_teacher` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(20) NOT NULL COMMENT '姓名',
  `f_sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别==>{"0":"未知","1":"男";"2":"女"}',
  `f_age` tinyint(3) NOT NULL DEFAULT '-1' COMMENT '年龄（为空，则值为-1）',
  `f_title` varchar(50) DEFAULT NULL COMMENT '职务',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='老师信息表';

-- 正在导出表  jta_demo_db2.t_teacher 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;
INSERT INTO `t_teacher` (`f_id`, `f_name`, `f_sex`, `f_age`, `f_title`) VALUES
	(1, '李老师', 2, 36, '特级教师');
/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
