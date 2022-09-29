package com.drlang.shadring;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drlang.shadring.entity.TbDevice;
import com.drlang.shadring.mapper.DeviceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class ShardingTest {
    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    void name() {
        for (int i = 0; i <10 ; i++) {
            TbDevice tbDevice = new TbDevice();
            tbDevice.setDeviceId((long) i);
            tbDevice.setDeviceType(i);
//            deviceMapper.delete(new QueryWrapper<TbDevice>().eq("device_id",i));
            deviceMapper.insert(tbDevice);
        }
    }

    @Test
    void query() {
        System.out.println("deviceMapper.selectList(new QueryWrapper<TbDevice>().eq(\"device_id\",1L)) = " + deviceMapper.selectList(new QueryWrapper<TbDevice>().eq("device_id", 1L)));
    }

    @Test
    void query2() {
        System.out.println("deviceMapper.selectList(new QueryWrapper<TbDevice>().between(\"device_id\",1,20)) = " + deviceMapper.selectList(new QueryWrapper<TbDevice>().between("device_id", 1, 20)));
    }
}
