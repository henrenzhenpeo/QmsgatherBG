package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 油墨密度- OD
 * @TableName df_up_bg_ink_density
 */
@TableName(value ="df_up_bg_ink_density")
@Data
public class DfUpBgInkDensity implements Serializable {
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
     * 日期
     */
    private String testDate;

    /**
     * 密度标准 >4.5
     */
    private Double densityLevel;

    /**
     * 时间（hh:ss）
     */
    private String testTime;

    /**
     * 密度值
     */
    private Double densityValue;

    /**
     * 状态（OK/NG）
     */
    private String state;

    /**
     * 数据上传人
     */
    private String uploadName;

    /**
     * 批次号
     */
    private String batchId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}