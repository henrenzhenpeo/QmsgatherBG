package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 油墨厚度+机油高度
 * @TableName df_up_bg_ink_land_height
 */
@TableName(value ="df_up_bg_ink_land_height")
@Data
public class DfUpBgInkLandHeight implements Serializable {
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
     * 状态
     */
    private String state;

    /**
     * 日期（yyyy-mm-dd）
     */
    private String testDate;

    /**
     * 测试时间（hh:ss）
     */
    private String testTime;

    /**
     * 油墨厚度
     */
    private String inkHeight;

    /**
     * 积油高度
     */
    private String oilHeight;

    /**
     * 上传人
     */
    private String uploadName;

    /**
     * 批次号
     */
    private String batchId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}