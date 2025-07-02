package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 应力测量详情
 * @TableName df_ort_stress_detail
 */
@TableName(value ="df_ort_stress_detail")
@Data
public class DfOrtStressDetail {
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
    @Excel(name = "测试时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date testTime;

    /**
     * 白夜班
     */
    @Excel(name = "白夜班")
    private String dayOrNight;

    /**
     * 批次，一天为一批
     */
    @Excel(name = "批次")
    private String batch;

    /**
     * 工序
     */
    @Excel(name = "工序")
    private String process;

    /**
     * 
     */
    @Excel(name = "cs")
    private Double cs;

    /**
     * 
     */
    @Excel(name = "csk")
    private Double csk;

    /**
     * 
     */
    @Excel(name = "dol")
    private Double dol;

    /**
     * 
     */
    @Excel(name = "ct")
    private Double ct;

    /**
     * 
     */
    @Excel(name = "doc")
    private Double doc;

    /**
     * 
     */
    @Excel(name = "tact")
    private Double tact;

    /**
     * 
     */
    @Excel(name = "ctta")
    private Double ctta;

    /**
     * 
     */
    @Excel(name = "gtValue")
    private Double gtValue;

    /**
     * 
     */
    @Excel(name = "创建时间",format = "yyyy-MM-dd HH:mm:ss")
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
        DfOrtStressDetail other = (DfOrtStressDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getFactoryTestTime() == null ? other.getFactoryTestTime() == null : this.getFactoryTestTime().equals(other.getFactoryTestTime()))
            && (this.getTestTime() == null ? other.getTestTime() == null : this.getTestTime().equals(other.getTestTime()))
            && (this.getDayOrNight() == null ? other.getDayOrNight() == null : this.getDayOrNight().equals(other.getDayOrNight()))
            && (this.getBatch() == null ? other.getBatch() == null : this.getBatch().equals(other.getBatch()))
            && (this.getProcess() == null ? other.getProcess() == null : this.getProcess().equals(other.getProcess()))
            && (this.getCs() == null ? other.getCs() == null : this.getCs().equals(other.getCs()))
            && (this.getCsk() == null ? other.getCsk() == null : this.getCsk().equals(other.getCsk()))
            && (this.getDol() == null ? other.getDol() == null : this.getDol().equals(other.getDol()))
            && (this.getCt() == null ? other.getCt() == null : this.getCt().equals(other.getCt()))
            && (this.getDoc() == null ? other.getDoc() == null : this.getDoc().equals(other.getDoc()))
            && (this.getTact() == null ? other.getTact() == null : this.getTact().equals(other.getTact()))
            && (this.getCtta() == null ? other.getCtta() == null : this.getCtta().equals(other.getCtta()))
            && (this.getGtValue() == null ? other.getGtValue() == null : this.getGtValue().equals(other.getGtValue()))
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
        result = prime * result + ((getCs() == null) ? 0 : getCs().hashCode());
        result = prime * result + ((getCsk() == null) ? 0 : getCsk().hashCode());
        result = prime * result + ((getDol() == null) ? 0 : getDol().hashCode());
        result = prime * result + ((getCt() == null) ? 0 : getCt().hashCode());
        result = prime * result + ((getDoc() == null) ? 0 : getDoc().hashCode());
        result = prime * result + ((getTact() == null) ? 0 : getTact().hashCode());
        result = prime * result + ((getCtta() == null) ? 0 : getCtta().hashCode());
        result = prime * result + ((getGtValue() == null) ? 0 : getGtValue().hashCode());
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
        sb.append(", cs=").append(cs);
        sb.append(", csk=").append(csk);
        sb.append(", dol=").append(dol);
        sb.append(", ct=").append(ct);
        sb.append(", doc=").append(doc);
        sb.append(", tact=").append(tact);
        sb.append(", ctta=").append(ctta);
        sb.append(", gtValue=").append(gtValue);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}