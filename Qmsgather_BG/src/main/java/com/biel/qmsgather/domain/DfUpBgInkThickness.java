package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * bg油墨厚度
 * @TableName df_up_bg_ink_thickness
 */
@TableName(value ="df_up_bg_ink_thickness")
@Data
public class DfUpBgInkThickness implements Serializable {
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
     * 日期（yyyy-mm-dd）
     */
    private String testDate;

    /**
     * 镀膜层数（log,一层，二层，三层，四层，五层，光油）
     */
    private String coatingLayers;

    /**
     * 状态（首检，巡检，换网板，待料，停机）
     */
    private String state;

    /**
     * 时间（hh:mm）
     */
    private String testTime;

    /**
     * 真值
     */
    private String trueValue;

    /**
     * 机台
     */
    private String machineCode;

    /**
     * 点位1
     */
    private String point1;

    /**
     * 点位2
     */
    private String ponit2;

    /**
     * 点位3
     */
    private String point3;

    /**
     * 平台
     */
    private String platform;

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

    //油墨厚度字表信息
    @TableField(exist = false)
    private List<DfUpBgInkThicknessChild> dfUpBgInkThicknessChildList;
}