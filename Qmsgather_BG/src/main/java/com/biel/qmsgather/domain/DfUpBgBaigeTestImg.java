package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * bg百格测试图片上传
 * @TableName df_up_bg_baige_test_img
 */
@TableName(value ="df_up_bg_baige_test_img")
@Data
public class DfUpBgBaigeTestImg implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工序
     */
    private String process;

    /**
     * 图片地址
     */
    private String imgAddress;

    /**
     * 图片编号
     */
    private String imgNumber;

    /**
     * 测试日期
     */
    private String testDate;

    /**
     * 批次号（工序+批次号）
     */
    private String batchId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}