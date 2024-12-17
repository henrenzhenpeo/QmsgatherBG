package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 油墨尺寸(厚度)
 * @TableName df_up_bg_ink_size_thickness
 */
@TableName(value ="df_up_bg_ink_size_thickness")
@Data
public class DfUpBgInkSizeThickness implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工厂(默认A3-MD)
     */
    private String factory;

    /**
     * 项目（默认C98B）
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
     * 日期（yyyy-mm-dd）
     */
    private String testDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}