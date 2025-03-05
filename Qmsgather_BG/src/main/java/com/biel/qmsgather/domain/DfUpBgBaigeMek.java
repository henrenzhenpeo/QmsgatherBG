package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * bg百格 mek
 * @TableName df_up_bg_baige_mek
 */
@TableName(value ="df_up_bg_baige_mek")
@Data
public class DfUpBgBaigeMek implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工序（mek）
     */
    @ApiModelProperty("工序（mek）")
    private String process;

    /**
     * 工厂
     */
    @ApiModelProperty("工厂")
    private String factory;

    /**
     * 项目
     */
    @ApiModelProperty("项目")
    private String project;

    /**
     * 工序
     */
    @ApiModelProperty("工序")
    private String production;

    /**
     * 颜色
     */
    private String color;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 测试日期（yyyy-mm-dd）
     */
    private String testDate;

    /**
     * 2d码
     */
    private String twoCode;



    private String no;

    /**
     * 测试时间（hh:mm）
     */
    private String testTime;

    /**
     * 测量结果（pass/fail默认pass）
     */
    private String measurementResult;

    /**
     * 状态
     */
    private String state;

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