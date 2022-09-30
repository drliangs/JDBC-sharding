package com.drlang.shadring.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TbDevice {
    private Long deviceId;
    private int deviceType;

}
