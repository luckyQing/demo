# flyway demo

## 简介
Flyway是独立于数据库的应用、管理并跟踪数据库变更的数据库版本管理工具。可以像SVN管理不同人的代码那样，管理不同人的sql脚本，从而做到数据库同步。

Flyway对数据库进行版本管理主要由flyway_schema_history表和6种命令完成，flyway_schema_history主要用于记录元数据，每种命令功能和解决的问题范围不一样。

## flyway 规范
### 1、sql 规范   
V+版本号(版本号的数字间以"."或"_"分隔开)+双下划线(用来分隔版本号和描述)+文件描述+后缀名

如：V20190829__1_init.sql

### 2、指令规范（migrate、clean、info、validate、baseline、repair） 

