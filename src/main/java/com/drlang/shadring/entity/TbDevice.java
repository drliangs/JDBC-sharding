package com.drlang.shadring.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TbDevice {
    @TableId("device_id")
    private Long deviceId;
    @TableField("device_type")
    private int deviceType;

}
