package com.liyulin.shading.jdbc.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Slf4j
public class YearShardingAlgorithm implements StandardShardingAlgorithm<LocalDateTime> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDateTime> shardingValue) {
//        return String.format("sharding_log_db_%d", shardingValue.getValue().getYear());
        String ds = String.format("dslog%d", shardingValue.getValue().getYear());
        log.info("ds={}", ds);
        return ds;
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