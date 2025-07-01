package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
    @Excel(name = "ID")
    private Integer id;

    /**
     * 批次
     */
    @Excel(name = "批次")
    private String batch;

    /**
     * 检测时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "检测时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    /**
     * 白夜班
     */
    @Excel(name = "班别")
    private String dayOrNight;

    /**
     * 型号
     */
    @Excel(name = "型号")
    private String project;

    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    /**
     * 序号
     */
    @Excel(name = "序号")
    private Integer no;

    /**
     * 点位1值
     */
    @Excel(name = "点位1")
    private Double point1;

    /**
     * 点位2值
     */
    @Excel(name = "点位2")
    private Double point2;

    /**
     * 点位3值
     */
    @Excel(name = "点位3")
    private Double point3;

    /**
     * 点位4值
     */
    @Excel(name = "点位4")
    private Double point4;

    /**
     * 点位5值
     */
    @Excel(name = "点位5")
    private Double point5;

    /**
     * 点位6值
     */
    @Excel(name = "点位6")
    private Double point6;

    /**
     * 点位7值
     */
    @Excel(name = "点位7")
    private Double point7;

    /**
     * 点位8值
     */
    @Excel(name = "点位8")
    private Double point8;

    /**
     * 点位9值
     */
    @Excel(name = "点位9")
    private Double point9;

    /**
     * 结果
     */
    @Excel(name = "结果")
    private String result;

    /**
     * OQC / FQC
     */
    @Excel(name = "OQC/FQC")
    private String oqcOrFqc;

    /**
     * 测试 丝印前 / 终烤后
     */
    @Excel(name = "测试工序")
    private String test;

    /**
     * 
     */
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Double location1;

    private Double location2;

    private Double location3;

    private Double topSurface;

    private String testRegion;

    @Excel(name = "工厂")
    private String factory;

    private String testStage;

    @Excel(name = "2D码")
    private String code2d;

    private String asCoating;
}