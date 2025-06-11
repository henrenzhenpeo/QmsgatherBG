package com.biel.qmsgather.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class DfUpBgClarityParticleGlossDetailVo {

    /**
     * 项目
     */
    private String project;

    /**
     * 颜色
     */
    private String color;

    /**
     * 工厂测试时间，是字符串
     */
    private String factoryTestTime;

    /**
     * 测试时间，用来筛选
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date testTime;

    /**
     * 白夜班
     */
    private String dayOrNight;

    /**
     * 批次，一天为一批
     */
    private String batch;

    /**
     * 工序
     */
    private String process;

    /**
     * 清晰度
     */
    private Double definition;

    /**
     * 颗粒度
     */
    private Double granularity;

    /**
     * 光泽度
     */
    private Double gloss;

    /**
     * 机台号
     */
    private String machineCode;

    /**
     * 状态
     */
    private String state;

}
