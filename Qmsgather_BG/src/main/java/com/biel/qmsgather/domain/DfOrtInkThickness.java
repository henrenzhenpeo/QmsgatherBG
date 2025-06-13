package com.biel.qmsgather.domain;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date testTime;

    /**
     * 测试序号
     */
    private Integer testNo;

    /**
     * 批次号
     */
    private String batch;

    /**
     * 
     */
    private Double cavity;

    /**
     * 
     */
    private Double ink1;

    /**
     * 
     */
    private Double ink2;

    /**
     * 
     */
    private Double ink3;

    /**
     * 
     */
    private Double ink4;

    /**
     * 白班/晚班
     */
    private String dayOrNignt;

    /**
     * ok/ng
     */
    private String result;


}