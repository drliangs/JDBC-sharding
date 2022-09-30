package com.drlang.shadring.algoritm.tables;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MyTableHintAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        String tableName = logicTableName + "_" + shardingValue.getValues().<Integer>toArray()[0];
        if (!availableTargetNames.contains(logicTableName)) {
            throw new UnsupportedOperationException("表" + logicTableName + "不存在");
        }
        return Collections.singletonList(tableName);
    }

}
