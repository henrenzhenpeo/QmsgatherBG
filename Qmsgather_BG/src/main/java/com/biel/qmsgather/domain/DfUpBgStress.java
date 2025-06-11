package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * bg 应力
 * @TableName df_up_bg_stress
 */
@TableName(value ="df_up_bg_stress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DfUpBgStress {
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
     * 产品型号
     */
    private String productModel;

    /**
     * 产品颜色
     */
    private String profuctColor;

    /**
     * 工序
     */
    private String process;

    /**
     * 日期
     */
    private String date;

    /**
     * 炉号
     */
    private String heatNumber;

    /**
     * 测试id
     */
    private Integer pointId;

    /**
     * 测试时间
     */
    private String testTime;

    /**
     * cs(MPa)
     */
    private Integer csValue;

    /**
     * csk(MPa)
     */
    private String cskValue;

    /**
     * dol(MPa)
     */
    private String dolValue;

    /**
     * ct(MPa)
     */
    private Integer ctValue;

    /**
     * doc(um)
     */
    private Integer docValue;

    /**
     * ct/ta
     */
    private Integer cttaDivValue;

    /**
     * ct*ta
     */
    private Integer cttaMulValue;

    /**
     * ct(mm)
     */
    private Integer gtValue;

    /**
     * 上传时间
     */
    private String updateTime;

    /**
     * 上传人
     */
    private String updateName;

    /**
     * 批次号
     */
    private String batchId;

}