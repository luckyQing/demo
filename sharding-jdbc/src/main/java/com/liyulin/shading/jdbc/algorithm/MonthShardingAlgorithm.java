package com.liyulin.shading.jdbc.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Slf4j
public class MonthShardingAlgorithm implements StandardShardingAlgorithm<LocalDateTime> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDateTime> shardingValue) {
        String db = String.format("sharding_log_db_%d", shardingValue.getValue().getYear());
        String table = String.format("%s_%s", shardingValue.getLogicTableName(), StringUtils.leftPad(String.valueOf(shardingValue.getValue().getMonthValue()), 2, '0'));
        String fullTable = db + "." + table;
        log.info("fullTable={}", fullTable);
        return fullTable;
//        return String.format("sharding_log_db_%d.%s_%s", shardingValue.getValue().getYear(), shardingValue.getLogicTableName(), StringUtils.leftPad(String.valueOf(shardingValue.getValue().getMonthValue()), 2, '0'));
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<LocalDateTime> shardingValue) {
        return Collections.emptyList();
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }
}
