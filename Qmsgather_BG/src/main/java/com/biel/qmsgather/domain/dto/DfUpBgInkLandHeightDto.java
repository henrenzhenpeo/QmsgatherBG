package com.biel.qmsgather.domain.dto;


import lombok.Data;

@Data
public class DfUpBgInkLandHeightDto {

    private Integer page;
    private Integer limit;

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
    private String startTestDate;
    private String endTestDate;
}
