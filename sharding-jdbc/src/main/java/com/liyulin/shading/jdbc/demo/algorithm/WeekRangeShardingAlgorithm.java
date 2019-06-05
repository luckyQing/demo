package com.liyulin.shading.jdbc.demo.algorithm;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.joda.time.DateTime;

import com.google.common.collect.Range;
import com.liyulin.shading.jdbc.demo.uitl.WeekShardingUtil;

public class WeekRangeShardingAlgorithm implements RangeShardingAlgorithm<String> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<String> shardingValue) {
		Range<String> range = shardingValue.getValueRange();

		DateTime startDateTime = WeekShardingUtil.parse(range.lowerEndpoint());
		int startDateTimeYear = startDateTime.getYear();
		int startDateTimeWeek = startDateTime.getWeekOfWeekyear();

		DateTime endDateTime = WeekShardingUtil.parse(range.upperEndpoint());
		int endDateTimeYear = endDateTime.getYear();
		int endDateTimeWeek = endDateTime.getWeekOfWeekyear();

		Collection<String> tables = new ArrayList<>();
		int maxWeek = 53;
		for (int year = startDateTimeYear; year <= endDateTimeYear; year++) {
			if (year != endDateTimeYear) {
				int startWeek = 1;
				if (year == startDateTimeYear) {
					startWeek = startDateTimeWeek;
				}
				for (int week = startWeek; week <= maxWeek; week++) {
					String tableName = WeekShardingUtil.getActualTableName(shardingValue.getLogicTableName(), year, week);
					tables.add(tableName);
				}
			} else {
				int startWeek = 1;
				if (startDateTimeYear == endDateTimeYear) {
					startWeek = startDateTimeWeek;
				}
				for (int week = startWeek; week <= endDateTimeWeek; week++) {
					String tableName = WeekShardingUtil.getActualTableName(shardingValue.getLogicTableName(), year, week);
					tables.add(tableName);
				}
			}
		}
		
		return tables;
	}
	
}