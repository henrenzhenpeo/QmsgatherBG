package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 实验--丝印-油墨密度-结果
 * @TableName df_ort_optical_density_result
 */
@TableName(value ="df_ort_optical_density_result")
@Data
public class DfOrtOpticalDensityResult {
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
     * 批次
     */
    @Excel(name = "批次")
    private String batch;

    /**
     * ink1结果
     */
    @Excel(name = "ink1结果")
    private String ink1Result;

    /**
     * ink2结果
     */
    @Excel(name = "ink2结果")
    private String ink2Result;

    /**
     * ink3结果
     */
    @Excel(name = "ink3结果")
    private String ink3Result;

    /**
     * ink4结果
     */
    @Excel(name = "ink4结果")
    private String ink4Result;

    /**
     * 结果
     */
    @Excel(name = "结果")
    private String result;

    /**
     * 检测时间
     */
    @Excel(name = "检测时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;

    /**
     * 
     */
    private Date createTime;

    //返回结果字段
    @TableField(exist = false)
    @ExcelCollection(name = "丝印-油墨密度明细")
    private List<DfOrtOpticalDensity> dfOrtOpticalDensityList;

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
        DfOrtOpticalDensityResult other = (DfOrtOpticalDensityResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getBatch() == null ? other.getBatch() == null : this.getBatch().equals(other.getBatch()))
            && (this.getInk1Result() == null ? other.getInk1Result() == null : this.getInk1Result().equals(other.getInk1Result()))
            && (this.getInk2Result() == null ? other.getInk2Result() == null : this.getInk2Result().equals(other.getInk2Result()))
            && (this.getInk3Result() == null ? other.getInk3Result() == null : this.getInk3Result().equals(other.getInk3Result()))
            && (this.getInk4Result() == null ? other.getInk4Result() == null : this.getInk4Result().equals(other.getInk4Result()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getCheckTime() == null ? other.getCheckTime() == null : this.getCheckTime().equals(other.getCheckTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getBatch() == null) ? 0 : getBatch().hashCode());
        result = prime * result + ((getInk1Result() == null) ? 0 : getInk1Result().hashCode());
        result = prime * result + ((getInk2Result() == null) ? 0 : getInk2Result().hashCode());
        result = prime * result + ((getInk3Result() == null) ? 0 : getInk3Result().hashCode());
        result = prime * result + ((getInk4Result() == null) ? 0 : getInk4Result().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getCheckTime() == null) ? 0 : getCheckTime().hashCode());
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
        sb.append(", batch=").append(batch);
        sb.append(", ink1Result=").append(ink1Result);
        sb.append(", ink2Result=").append(ink2Result);
        sb.append(", ink3Result=").append(ink3Result);
        sb.append(", ink4Result=").append(ink4Result);
        sb.append(", result=").append(result);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}