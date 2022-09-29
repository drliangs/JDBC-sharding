package com.drlang.shadring.algoritm.tables;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class MyTableStandardPreciseAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long value = shardingValue.getValue();
        String logicTableName = shardingValue.getLogicTableName() + "_" + (value % 2);
        if (!availableTargetNames.contains(logicTableName)) {
            throw new UnsupportedOperationException("表" + logicTableName + "不存在");
        }
        return logicTableName;
    }
}
