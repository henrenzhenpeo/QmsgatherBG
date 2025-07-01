package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 光泽度度测量详情
 * @TableName df_ort_gloss_detail
 */
@TableName(value ="df_ort_gloss_detail")
@Data
public class DfOrtGlossDetail {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目
     */
    @Excel(name = "项目")
    private String project;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    /**
     * 工厂测试时间，是字符串
     */
    @Excel(name = "工厂测试时间")
    private String factoryTestTime;

    /**
     * 测试时间，用来筛选
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date testTime;

    /**
     * 白夜班
     */
    @Excel(name = "白夜班")
    private String dayOrNight;

    /**
     * 批次，一天为一批
     */
    @Excel(name = "批次号")
    private String batch;

    /**
     * 工序
     */
    @Excel(name = "工序")
    private String process;

    /**
     * 测量值
     */
    @Excel(name = "测量值")
    private Double value;

    /**
     * 
     */
    private Date createTime;


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DfOrtGlossDetail other = (DfOrtGlossDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getFactoryTestTime() == null ? other.getFactoryTestTime() == null : this.getFactoryTestTime().equals(other.getFactoryTestTime()))
            && (this.getTestTime() == null ? other.getTestTime() == null : this.getTestTime().equals(other.getTestTime()))
            && (this.getDayOrNight() == null ? other.getDayOrNight() == null : this.getDayOrNight().equals(other.getDayOrNight()))
            && (this.getBatch() == null ? other.getBatch() == null : this.getBatch().equals(other.getBatch()))
            && (this.getProcess() == null ? other.getProcess() == null : this.getProcess().equals(other.getProcess()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getFactoryTestTime() == null) ? 0 : getFactoryTestTime().hashCode());
        result = prime * result + ((getTestTime() == null) ? 0 : getTestTime().hashCode());
        result = prime * result + ((getDayOrNight() == null) ? 0 : getDayOrNight().hashCode());
        result = prime * result + ((getBatch() == null) ? 0 : getBatch().hashCode());
        result = prime * result + ((getProcess() == null) ? 0 : getProcess().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", project=").append(project);
        sb.append(", color=").append(color);
        sb.append(", factoryTestTime=").append(factoryTestTime);
        sb.append(", testTime=").append(testTime);
        sb.append(", dayOrNight=").append(dayOrNight);
        sb.append(", batch=").append(batch);
        sb.append(", process=").append(process);
        sb.append(", value=").append(value);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}