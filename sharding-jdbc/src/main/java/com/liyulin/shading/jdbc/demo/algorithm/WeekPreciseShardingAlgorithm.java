package com.liyulin.shading.jdbc.demo.algorithm;

import java.util.Collection;
import java.util.Date;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.joda.time.DateTime;

/**
 * 按周分片
 * 
 * @author liyulin
 * @date 2019年6月3日 下午9:29:10
 */
public class WeekPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
		DateTime dateTime = new DateTime(shardingValue.getValue().getTime());
		int year = dateTime.getYear();
		int week = dateTime.getWeekOfWeekyear();
		// t_product_info_2019_23
		StringBuilder tableName = new StringBuilder(32);
		tableName.append(shardingValue.getLogicTableName())
				 .append("_")
				 .append(year)
				 .append("_")
				 .append(week);
		return tableName.toString();
	}

}