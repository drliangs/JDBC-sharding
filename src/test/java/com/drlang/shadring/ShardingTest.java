package com.drlang.shadring;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.drlang.shadring.entity.TbDevice;
import com.drlang.shadring.entity.TbDeviceInfo;
import com.drlang.shadring.entity.TbDeviceType;
import com.drlang.shadring.mapper.DeviceInfoMapper;
import com.drlang.shadring.mapper.DeviceMapper;
import com.drlang.shadring.mapper.DeviceTypeMapper;
import lombok.var;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlTableRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.SpringBootShardingRuleConfigurationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.BiConsumer;

@SpringBootTest

public class ShardingTest {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceInfoMapper infoMapper;
    @Autowired
    private DeviceTypeMapper typeMapper;
    @Autowired
    private SpringBootShardingRuleConfigurationProperties properties;

    @Test
    void namez() {
        properties.getTables().forEach(new BiConsumer<String, YamlTableRuleConfiguration>() {
            @Override
            public void accept(String s, YamlTableRuleConfiguration yamlTableRuleConfiguration) {
                System.out.println("yamlTableRuleConfiguration.getTableStrategy().getComplex().getShardingColumns() = " + yamlTableRuleConfiguration.getTableStrategy().getComplex().getShardingColumns());
            }

            ;
        });
    }

    @Test
    void name() {
        for (int i = 0; i < 10; i++) {
            TbDevice tbDevice = new TbDevice();
            tbDevice.setDeviceId((long) i);
            tbDevice.setDeviceType(i);
//            deviceMapper.delete(new QueryWrapper<TbDevice>().eq("device_id",i));
            deviceMapper.insert(tbDevice);
        }
    }

    @Test
    void query() {
        System.out.println("deviceMapper.selectList(new QueryWrapper<TbDevice>().eq(\"device_id\",1L)) = " + deviceMapper.selectList(new QueryWrapper<TbDevice>().eq("device_id", 2L)));
    }

    @Test
    void query2() {
//        QueryWrapper<TbDevice> tbDeviceQueryWrapper = new QueryWrapper<TbDevice>().select("device_id");
//        System.out.println("tbDeviceQueryWrapper.between(\"device_id\",1,20).getSqlSelect() = " + tbDeviceQueryWrapper.between("device_id", 1, 20).getSqlSelect());
//        System.out.println("deviceMapper.selectList(new QueryWrapper<TbDevice>().between(\"device_id\",1,20)) = " + deviceMapper.selectList(tbDeviceQueryWrapper));
        LambdaQueryWrapper<TbDevice> between = Wrappers.lambdaQuery(new TbDevice()).select(TbDevice::getDeviceId).between(TbDevice::getDeviceId, 1L, 10L);
        System.out.println("between.toString() = " + between.getSqlSelect());
        System.out.println("deviceMapper.selectList(Wrappers.lambdaQuery(new TbDevice()).select(TbDevice::getDeviceId).between(TbDevice::getDeviceId,1,10L)) = " +
                deviceMapper.selectList(Wrappers.<TbDevice>lambdaQuery().select(TbDevice::getDeviceId)
                        .between(TbDevice::getDeviceId, 1L, 10L)));

    }

    @Test
    void quey() {
        var wrapper = new LambdaQueryWrapper<TbDevice>().
                between(TbDevice::getDeviceId, 1L, 10L).
                eq(TbDevice::getDeviceType, 5);
        System.out.println("deviceMapper.selectList(wrapper) = " + deviceMapper.selectList(wrapper));
    }

    @Test
    void testHint() {
        HintManager instance = HintManager.getInstance();
        instance.addDatabaseShardingValue("tb_device", 0);
        deviceMapper.selectList(null).forEach(System.out::println);

    }

    @Test
    void testInsertDeviceInfo() {
        for (int i = 0; i < 10; i++) {
            TbDevice deviceInfo = new TbDevice();
            deviceInfo.setDeviceId((long) i);
            deviceInfo.setDeviceType(i);
            deviceMapper.insert(deviceInfo);
            TbDeviceInfo tbDeviceInfo = new TbDeviceInfo();
            tbDeviceInfo.setDeviceId((long) i);
            tbDeviceInfo.setDeviceIntro("" + i);
            infoMapper.insert(tbDeviceInfo);
        }
    }

    @Test
    void join() {
        infoMapper.queryDeviceInfo().forEach(System.out::println);
    }

    @Test
    void testInsertDeviceType() {
        TbDeviceType tbDeviceType = new TbDeviceType();
        tbDeviceType.setTypeId(1);
        tbDeviceType.setTypeName("人脸考勤");


        TbDeviceType tbDeviceType1 = new TbDeviceType();

    }
}
