package com.drlang.shadring.algoritm.tables;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MyTableComplexAlgorithm implements ComplexKeysShardingAlgorithm<Integer> {
    private SpringBootShardingRuleConfigurationProperties properties;

//    public MyTableComplexAlgorithm(SpringBootShardingRuleConfigurationProperties properties) {
//        this.properties = properties;
//    }

    @Autowired
    public void setProperties(SpringBootShardingRuleConfigurationProperties properties) {
        System.out.println("properties = " + properties);
        this.properties = properties;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Integer> shardingValue) {

        Collection<Integer> deviceTypeValues = shardingValue.getColumnNameAndShardingValuesMap().get("device_type");
        List<String> tables = new ArrayList<>();
        for (Integer deviceTypeValue : deviceTypeValues) {
            String tableName = shardingValue.getLogicTableName() + "_" + (deviceTypeValue % 2);
            tables.add(tableName);
        }
        return tables;
    }
}
