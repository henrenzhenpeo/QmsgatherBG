package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.aspectj.apache.bcel.classfile.Code;

/**
 * 实验--油墨水滴角
 * @TableName df_ort_oil_ink
 */
@TableName(value ="df_ort_oil_ink")
@Data
public class DfOrtOilInk {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 批次
     */
    private String batch;

    /**
     * 检测时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

    /**
     * 白夜班
     */
    private String dayOrNight;

    /**
     * 型号
     */
    private String project;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 颜色
     */
    private String color;

    /**
     * 序号
     */
    private Integer no;

    /**
     * 点位1值
     */
    private Double point1;

    /**
     * 点位2值
     */
    private Double point2;

    /**
     * 点位3值
     */
    private Double point3;

    /**
     * 点位4值
     */
    private Double point4;

    /**
     * 点位5值
     */
    private Double point5;

    /**
     * 点位6值
     */
    private Double point6;

    /**
     * 点位7值
     */
    private Double point7;

    /**
     * 点位8值
     */
    private Double point8;

    /**
     * 点位9值
     */
    private Double point9;

    /**
     * 结果
     */
    private String result;

    /**
     * OQC / FQC
     */
    private String oqcOrFqc;

    /**
     * 测试 丝印前 / 终烤后
     */
    private String test;

    /**
     * 
     */
    private Date createTime;

    private Double location1;

    private Double location2;

    private Double location3;

    private Double topSurface;

    private String testRegion;

    private String factory;

    private String testStage;

    private String code2d;

    private String asCoating;
}