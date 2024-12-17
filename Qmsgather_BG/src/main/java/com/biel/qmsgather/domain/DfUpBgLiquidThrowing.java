package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * bg 液抛
 * @TableName df_up_bg_liquid_throwing
 */
@TableName(value ="df_up_bg_liquid_throwing")
@Data
public class DfUpBgLiquidThrowing implements Serializable {
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
     * 日期（yyyy-mm-dd）
     */
    private String testDate;

    /**
     * 测试时间（hh:mm）
     */
    private String testTime;

    /**
     * 清晰度
     */
    private Double definition;

    /**
     * 颗粒度
     */
    private Double granularity;

    /**
     * 光泽度
     */
    private Double gloss;

    /**
     * 槽号
     */
    private String slotNumber;

    /**
     * 状态
     */
    private String state;

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 上传人
     */
    private String uploadName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}