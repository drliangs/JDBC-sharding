package com.drlang.shadring.algoritm.database;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class MyDataBaseStandardAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        Long value = preciseShardingValue.getValue();
        String columnName = preciseShardingValue.getColumnName();
        System.out.println("columnName = " + columnName);
        System.out.println("logicTableName = " + logicTableName);
        String databaseName = "ds" + (value % 2);
        if (!collection.contains(databaseName)) {
            throw new UnsupportedOperationException("数据源" + databaseName + "不存在");
        }


        return databaseName;
    }
}
