package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * bg 百格测试/煮水百格测试
 * @TableName df_up_bg_baige_test
 */
@TableName(value ="df_up_bg_baige_test")
@Data
public class DfUpBgBaigeTest implements Serializable {
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
     * 工厂
     */
    private String factory;

    /**
     * 项目
     */
    private String project;

    /**
     * 颜色
     */
    private String color;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 状态
     */
    private String state;

    /**
     * 测试日期（yyyy-mm-dd）
     */
    private String testDate;

    /**
     * 2D码
     */
    private String twoCode;

    /**
     * 测试时间（hh:mm）
     */
    private String testTime;

    /**
     * area1
     */
    private String area1;

    /**
     * area2
     */
    private String area2;

    /**
     * area3
     */
    private String area3;

    /**
     * area4
     */
    private String area4;

    /**
     * area15
     */
    private String area5;

    /**
     * area6
     */
    private String area6;

    /**
     * area7
     */
    private String area7;

    /**
     * conclusion
     */
    private String conclusion;

    /**
     * 上传人
     */
    private String uploadName;

    /**
     * 批次号
     */
    private String batchId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}