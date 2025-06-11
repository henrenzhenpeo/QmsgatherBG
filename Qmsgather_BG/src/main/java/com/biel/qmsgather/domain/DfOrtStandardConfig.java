package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName df_ort_standard_config
 */
@TableName(value ="df_ort_standard_config")
@Data
public class DfOrtStandardConfig {
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
     * 工序
     */
    private String process;

    /**
     * 类型，粗糙度/光泽度等
     */
    private String type;

    /**
     * 标准-最小值
     */
    private Double standardMin;

    /**
     * 标准-最大值
     */
    private Double standardMax;

    /**
     * 
     */
    private Date createTime;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 顺序
     */
    private String standardItem;
}