package com.biel.qmsgather.domain;

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
    private String project;

    /**
     * 颜色
     */
    private String color;

    /**
     * 批次
     */
    private String batch;

    /**
     * 工序
     */
    private String process;

    /**
     * 班次
     */
    private String dayOrNight;

    /**
     * 测试数量
     */
    private Integer testNum;

    /**
     * ok数量
     */
    private Integer okNum;

    /**
     * 良率
     */
    private Double okRate;

    /**
     * 结果
     */
    private String result;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date testTime;

    private Integer isDeleted;

    //返回结果字段
    @TableField(exist = false)
    private List<DfOrtGlossDetail> dfOrtGlossDetailList;

}