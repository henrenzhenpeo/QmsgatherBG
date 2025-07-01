package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * ort油墨厚度
 * @TableName df_ort_ink_thickness
 */
@TableName(value ="df_ort_ink_thickness")
@Data
public class DfOrtInkThickness {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工厂
     */
    @Excel(name = "工厂")
    private String factory;

    /**
     * 项目
     */
    @Excel(name = "项目")
    private String project;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;

    /**
     * 测试日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "测试日期")
    private Date testTime;

    /**
     * 测试序号
     */
    @Excel(name = "测试序号")
    private Integer testNo;

    /**
     * 批次号
     */
    @Excel(name = "批次号")
    private String batch;

    /**
     * 
     */
    @Excel(name = "cavity")
    private Double cavity;

    /**
     * 
     */
    @Excel(name = "ink1")
    private Double ink1;

    /**
     * 
     */
    @Excel(name = "ink2")
    private Double ink2;

    /**
     * 
     */
    @Excel(name = "ink3")
    private Double ink3;

    /**
     * 
     */
    @Excel(name = "ink4")
    private Double ink4;

    /**
     * 白班/晚班
     */
    @Excel(name = "白班/晚班")
    private String dayOrNignt;

    /**
     * ok/ng
     */
    @Excel(name = "结果")
    private String result;


}