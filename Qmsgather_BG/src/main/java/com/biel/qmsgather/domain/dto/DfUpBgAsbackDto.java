package com.biel.qmsgather.domain.dto;

import lombok.Data;

@Data
public class DfUpBgAsbackDto {

    private Integer page;
    private Integer limit;

    /**
     * 工序名称
     */
    private String process;

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
    private String startTestDate;
    private String endTestDate;


}
