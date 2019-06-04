package com.liyulin.shading.jdbc.demo.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.joda.time.DateTime;

import com.google.common.collect.Range;

public class WeekRangeShardingAlgorithm implements RangeShardingAlgorithm<Date> {

	@Override
	public Collection<String> doSharding(Collection<String> availableTargetNames,
			RangeShardingValue<Date> shardingValue) {
		Range<Date> range = shardingValue.getValueRange();
		Date start = range.lowerEndpoint();
		Date end = range.upperEndpoint();

		DateTime startDateTime = new DateTime(start.getTime());
		int startDateTimeYear = startDateTime.getYear();
		int startDateTimeWeek = startDateTime.getWeekOfWeekyear();

		DateTime endDateTime = new DateTime(end.getTime());
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
					StringBuilder tableName = new StringBuilder(32);
					tableName.append(shardingValue.getLogicTableName())
							.append("_")
							.append(year)
							.append("_")
							.append(week);
					tables.add(tableName.toString());
				}
			} else {
				int startWeek = 1;
				if (startDateTimeYear == endDateTimeYear) {
					startWeek = startDateTimeWeek;
				}
				for (int week = startWeek; week <= endDateTimeWeek; week++) {
					StringBuilder tableName = new StringBuilder(32);
					tableName.append(shardingValue.getLogicTableName())
							.append("_")
							.append(year)
							.append("_")
							.append(week);
					tables.add(tableName.toString());
				}
			}
		}
		
		return tables;
	}

}