package com.biel.qmsgather.domain.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.biel.qmsgather.domain.DfOrtClArityDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DfUpBgClarityParticleGlossVo {
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

    @TableField(exist = false)
    private List<DfUpBgClarityParticleGlossDetailVo> DfUpBgClarityParticleGlossDetailVo;

}
