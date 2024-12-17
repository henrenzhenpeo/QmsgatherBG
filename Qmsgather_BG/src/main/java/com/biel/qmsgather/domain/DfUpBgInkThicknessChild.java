package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * bg油墨密度字表
 * @TableName df_up_bg_ink_thickness_child
 */
@TableName(value ="df_up_bg_ink_thickness_child")
@Data
public class DfUpBgInkThicknessChild implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 名称编号
     */
    private String nameId;

    /**
     * 点位1
     */
    private String point1;

    /**
     * 点位2
     */
    private String point2;

    /**
     * 点位3
     */
    private String point3;

    /**
     * 平台
     */
    private String platform;

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
     * 批次号
     */
    private String batchId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}