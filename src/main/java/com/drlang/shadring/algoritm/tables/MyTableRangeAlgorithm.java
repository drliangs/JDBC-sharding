package com.drlang.shadring.algoritm.tables;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

public class MyTableRangeAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        return Arrays.asList(logicTableName + "_0", logicTableName + "_1");
    }
}
