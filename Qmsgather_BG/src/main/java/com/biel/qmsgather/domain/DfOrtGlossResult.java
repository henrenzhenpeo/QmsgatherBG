package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 光泽度测量结果
 * @TableName df_ort_gloss_result
 */
@TableName(value ="df_ort_gloss_result")
@Data
public class DfOrtGlossResult {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 批次
     */
    @Excel(name = "批次号")
    private String batch;

    /**
     * 工序
     */
    @Excel(name = "工序")
    private String process;

    /**
     * 班次
     */
    @Excel(name = "班次")
    private String dayOrNight;

    /**
     * 测试数量
     */
    @Excel(name = "测试数量")
    private Integer testNum;

    /**
     * ok数量
     */
    @Excel(name = "ok数量")
    private Integer okNum;

    /**
     * 良率
     */
    @Excel(name = "良率")
    private Double okRate;

    /**
     * 结果
     */
    @Excel(name = "结果")
    private String result;

    /**
     * 
     */
    @Excel(name = "创建时间")
    private Date createTime;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "测试时间")
    private Date testTime;


    //返回结果字段
    @TableField(exist = false)
    @ExcelCollection(name = "光泽度明细")
    private List<DfOrtGlossDetail> dfOrtGlossDetailList;

}