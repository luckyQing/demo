package com.liyulin.shading.jdbc.uitl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.alibaba.fastjson.JSON;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据库表工具类
 * 
 * @author liyulin
 * @date 2019年6月4日 下午10:07:34
 */
@UtilityClass
@Slf4j
public class DbTableUtil {

	/** copy表结构sql */
	private static final String COPY_TABLE_SQL = "CREATE TABLE ${targetTableName} LIKE ${sourceTableName}";
	/** 删除表sql */
	private static final String DROP_TABLE_SQL = "DROP TABLE ${tableName}";
	/** 删除表数据sql */
	private static final String DELETE_TABLE_SQL = "DELETE FROM ${tableName}";

	/**
	 * （在当前库）复制表结构
	 * 
	 * @param sourceTableName   被复制的表名
	 * @param targetTableName   复制后的表名
	 * @param sqlSessionFactory
	 */
	public static void copyTableSchema(String sourceTableName, String targetTableName,
			SqlSessionFactory sqlSessionFactory) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			Map<String, String> sqlParams = new HashMap<>();
			sqlParams.put("targetTableName", targetTableName);
			sqlParams.put("sourceTableName", sourceTableName);

			SqlMapper sqlMapper = new SqlMapper(sqlSession);
			// 此处不能用“selectOne”，否则sharding-jdbc会报错
			sqlMapper.update(COPY_TABLE_SQL, sqlParams);
		} finally {
			log.info(COPY_TABLE_SQL + "==>targetTableName=" + targetTableName + "; sourceTableName=" + sourceTableName);
		}
	}

	/**
	 * （在当前库）创建表（如果不存在）
	 * 
	 * @param sourceTableName   源表
	 * @param targetTableName   待创建的表
	 * @param sqlSessionFactory
	 */
	public static void createTableIfAbsent(String sourceTableName, String targetTableName,
			SqlSessionFactory sqlSessionFactory) {
		try {
			boolean exist = existTable(targetTableName, sqlSessionFactory);
			if (!exist) {
				copyTableSchema(sourceTableName, targetTableName, sqlSessionFactory);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * （在当前库）判断表是否已存在
	 * 
	 * @param tableName         表名
	 * @param sqlSessionFactory
	 * @return
	 * @throws Exception
	 */
	public static boolean existTable(String tableName, SqlSessionFactory sqlSessionFactory) {
		List<String> tables = queryTables(tableName, false, sqlSessionFactory);
		return tables.size() == 1 && tables.get(0).equals(tableName);
	}

	/**
	 * 通过表名前缀查询所有的表名
	 * 
	 * @param tableNamePrefix   表名前缀
	 * @param sqlSessionFactory
	 * @return
	 */
	public static List<String> queryTablesByPrefix(String tableNamePrefix, SqlSessionFactory sqlSessionFactory) {
		return queryTables(tableNamePrefix, true, sqlSessionFactory);
	}

	/**
	 * 根据表名查询满足条件的表
	 * 
	 * @param tableName         表名
	 * @param prefix            是否表名前缀匹配
	 * @param sqlSessionFactory
	 * @return
	 */
	private static List<String> queryTables(String tableName, Boolean prefix, SqlSessionFactory sqlSessionFactory) {
		if (prefix) {
			tableName += "%";
		}
		List<String> tablesWithPrefix = new ArrayList<>();
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			Connection connection = sqlSession.getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
			while (resultSet.next()) {
				tablesWithPrefix.add(resultSet.getString(3));
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			log.info("查询相关表tableName={}; result={}", tableName, JSON.toJSONString(tablesWithPrefix));
		}
		return tablesWithPrefix;
	}

	/**
	 * 删除表数据
	 * 
	 * @param tableName         表名
	 * @param sqlSessionFactory
	 */
	public static void delete(String tableName, SqlSessionFactory sqlSessionFactory) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			Map<String, String> sqlParams = new HashMap<>();
			sqlParams.put("tableName", tableName);

			SqlMapper sqlMapper = new SqlMapper(sqlSession);
			sqlMapper.delete(DELETE_TABLE_SQL, sqlParams);
		} finally {
			log.info("删除表[{}]（tableName={}）数据", DELETE_TABLE_SQL, tableName);
		}
	}

	/**
	 * 删除所有逻辑表
	 * 
	 * @param logicTableName    逻辑表
	 * @param sqlSessionFactory
	 */
	public static void dropAllLogicTables(String logicTableName, SqlSessionFactory sqlSessionFactory) {
		List<String> tableNames = queryTablesByPrefix(logicTableName, sqlSessionFactory);
		if (tableNames.isEmpty()) {
			return;
		}
		tableNames = tableNames.stream().filter(item -> !item.equals(logicTableName)).collect(Collectors.toList());

		dropTables(tableNames, sqlSessionFactory);
	}

	/**
	 * 删除所有逻辑表
	 * 
	 * @param tableNames        待drop的表名
	 * @param sqlSessionFactory
	 */
	public static void dropTables(List<String> tableNames, SqlSessionFactory sqlSessionFactory) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
			SqlMapper sqlMapper = new SqlMapper(sqlSession);

			Map<String, String> sqlParams = new HashMap<>();
			for (int i = 0; i < tableNames.size(); i++) {
				String tableName = tableNames.get(i);
				sqlParams.put("tableName", tableName);
				sqlMapper.update(DROP_TABLE_SQL, sqlParams);

				sqlParams.clear();
			}
		} finally {
			log.info("{}；tableName={}", DROP_TABLE_SQL, JSON.toJSONString(tableNames));
		}
	}

	/**
	 * 清空表（drop逻辑表对应的所有真实表，删除逻辑表哦数据）
	 * 
	 * @param logicTableName    逻辑表
	 * @param sqlSessionFactory
	 */
	public static void cleanTable(String logicTableName, SqlSessionFactory sqlSessionFactory) {
		dropAllLogicTables(logicTableName, sqlSessionFactory);
		delete(logicTableName, sqlSessionFactory);
	}

}