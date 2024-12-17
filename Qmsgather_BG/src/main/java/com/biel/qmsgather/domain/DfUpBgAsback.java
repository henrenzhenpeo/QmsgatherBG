package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * bg AS后
 * @TableName df_up_bg_asback
 */
@TableName(value ="df_up_bg_asback")
@Data
public class DfUpBgAsback implements Serializable {
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
     * 工序
     */
    private String process;

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
     * 日期
     */
    private String testDate;

    /**
     * 测试时间（yyyy-mm-dd hh:ss）
     */
    private String testTime;

    /**
     * 光泽度
     */
    private String gloss;

    /**
     * 机台号
     */
    private String machineCode;

    /**
     * 判定
     */
    private String judge;

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