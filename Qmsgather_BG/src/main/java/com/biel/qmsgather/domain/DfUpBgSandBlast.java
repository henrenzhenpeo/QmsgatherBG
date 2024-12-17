package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * bg喷砂
 * @TableName df_up_bg_sand_blast
 */
@TableName(value ="df_up_bg_sand_blast")
@Data
public class DfUpBgSandBlast implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工序名称
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
     * 测试日期
     */
    private String testDate;

    /**
     * 测试时间（yyyy-mm-dd hh:ss）
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
     * 机台号
     */
    private String machineCode;

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