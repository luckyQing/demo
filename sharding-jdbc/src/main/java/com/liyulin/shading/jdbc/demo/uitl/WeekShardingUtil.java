package com.liyulin.shading.jdbc.demo.uitl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import lombok.experimental.UtilityClass;

/**
 * 按周分片工具类
 * 
 * @author liyulin
 * @date 2019年6月5日 上午9:05:58
 */
@UtilityClass
public class WeekShardingUtil {

	private static Cache<Class<?>, String> logicTableNameCache = CacheBuilder.newBuilder()
			// 设置并发级别
			.concurrencyLevel(Runtime.getRuntime().availableProcessors() << 1)
			// 设置写缓存7天后过期
			.expireAfterWrite(7, TimeUnit.DAYS)
			// 设置缓存最大容量，超过之后就会按照LRU最近虽少使用算法来移除缓存项
			.maximumSize(1024)
			// 使用弱引用
			.weakValues().build();

	/**
	 * 获取真实表名
	 * 
	 * @param date
	 * @param logicTableName
	 * @return
	 */
	public static String getActualTableName(Date date, String logicTableName) {
		return getActualTableName(new DateTime(date), logicTableName);
	}

	/**
	 * 获取真实表名
	 * 
	 * @param dateTime
	 * @param logicTableName
	 * @return
	 */
	public static String getActualTableName(DateTime dateTime, String logicTableName) {
		int year = dateTime.getYear();
		int week = dateTime.getWeekOfWeekyear();
		// t_product_info_2019_23
		return getActualTableName(logicTableName, year, week);
	}

	/**
	 * 获取真实的表名
	 * 
	 * @param logicTableName
	 * @param year
	 * @param week
	 * @return
	 */
	public static String getActualTableName(String logicTableName, int year, int week) {
		StringBuilder tableName = new StringBuilder(32);
		tableName.append(logicTableName).append("_").append(year).append("_").append(week);

		return tableName.toString();
	}

	/**
	 * 获取逻辑表名
	 * 
	 * @param entityClass 表对应的实体类
	 * @return
	 */
	public static String getLogicTableName(Class<?> entityClass) {
		Table table = AnnotationUtils.findAnnotation(entityClass, Table.class);
		Assert.isTrue(table != null, "The Annotation of Table can not be null!");

		return table.name();
	}

	/**
	 * 获取逻辑表名
	 * 
	 * @param entityClass 表对应的实体类
	 * @param cache       是否缓存
	 * @return
	 */
	public static String getLogicTableName(Class<?> entityClass, boolean cache) {
		if (cache) {
			String logicTableName = logicTableNameCache.getIfPresent(entityClass);
			if (StringUtils.isEmpty(logicTableName)) {
				logicTableName = getLogicTableName(entityClass);
				if (!StringUtils.isEmpty(logicTableName)) {
					logicTableNameCache.put(entityClass, logicTableName);
				}
			}

			return logicTableName;
		}

		return getLogicTableName(entityClass);
	}

}