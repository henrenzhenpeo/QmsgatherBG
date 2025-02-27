package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * bg水滴角
 * @TableName df_up_bg_drip
 */
@TableName(value ="df_up_bg_drip")
@Data
public class DfUpBgDrip implements Serializable {
    /**
     * id
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
     * 工序
     */
    private String process;

    /**
     * 日期（yyyy-mm-dd）
     */
    private String testDate;

    /**
     * 测试日期（yyyy-mm-dd）
     */
    private String testDate2;

    /**
     * 测试班别
     */
    private String testClass;

    /**
     * 时间（hh:mm）
     */
    private String testTime;

    /**
     * 测试值1
     */
    private Double value1;

    /**
     * 测试值2
     */
    private Double value2;

    /**
     * 测试值3
     */
    private Double value3;

    /**
     * 测试值4
     */
    private Double value4;

    /**
     * 测试值5
     */
    private Double value5;

    /**
     * 测试值6
     */
    private Double value6;

    /**
     * 测试值7
     */
    private Double value7;

    /**
     * 测试值8
     */
    private Double value8;

    /**
     * 测试值9
     */
    private Double value9;

    /**
     * 上传人
     */
    private String uploadName;

    /**
     * 批次号
     */
    private String batchId;



    /**
     * 测试数量
     */
    private String testNumber;

    /**
     * 类型
     */
    private String type;

    /**
     * Excel处理
     */
    private String excelProcess;

    /**
     * Excel列表
     */
    private String excelList;

    /**
     * 备注
     */
    private String noted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}