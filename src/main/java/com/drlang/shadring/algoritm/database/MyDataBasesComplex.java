package com.drlang.shadring.algoritm.database;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyDataBasesComplex implements ComplexKeysShardingAlgorithm<Integer> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Integer> shardingValue) {
        Collection<Integer> deviceType = shardingValue.getColumnNameAndShardingValuesMap().get("device_type");
        List<String> database = new ArrayList<>();
        System.out.println("z");
        for (Integer integer : deviceType) {
            String databaseName= "ds" + (integer % 2);
            database.add(databaseName);
        }
        return database;
    }
}
