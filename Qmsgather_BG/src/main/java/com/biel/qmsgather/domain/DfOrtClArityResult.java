package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 清晰度测量结果
 * @TableName df_ort_cl_arity_result
 */
@TableName(value ="df_ort_cl_arity_result")
@Data
public class DfOrtClArityResult {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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

    //返回结果字段
    @TableField(exist = false)
    private List<DfOrtClArityDetail> dfOrtClArityDetailList;

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
        DfOrtClArityResult other = (DfOrtClArityResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getBatch() == null ? other.getBatch() == null : this.getBatch().equals(other.getBatch()))
            && (this.getProcess() == null ? other.getProcess() == null : this.getProcess().equals(other.getProcess()))
            && (this.getDayOrNight() == null ? other.getDayOrNight() == null : this.getDayOrNight().equals(other.getDayOrNight()))
            && (this.getTestNum() == null ? other.getTestNum() == null : this.getTestNum().equals(other.getTestNum()))
            && (this.getOkNum() == null ? other.getOkNum() == null : this.getOkNum().equals(other.getOkNum()))
            && (this.getOkRate() == null ? other.getOkRate() == null : this.getOkRate().equals(other.getOkRate()))
            && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getTestTime() == null ? other.getTestTime() == null : this.getTestTime().equals(other.getTestTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getBatch() == null) ? 0 : getBatch().hashCode());
        result = prime * result + ((getProcess() == null) ? 0 : getProcess().hashCode());
        result = prime * result + ((getDayOrNight() == null) ? 0 : getDayOrNight().hashCode());
        result = prime * result + ((getTestNum() == null) ? 0 : getTestNum().hashCode());
        result = prime * result + ((getOkNum() == null) ? 0 : getOkNum().hashCode());
        result = prime * result + ((getOkRate() == null) ? 0 : getOkRate().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getTestTime() == null) ? 0 : getTestTime().hashCode());
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
        sb.append(", process=").append(process);
        sb.append(", dayOrNight=").append(dayOrNight);
        sb.append(", testNum=").append(testNum);
        sb.append(", okNum=").append(okNum);
        sb.append(", okRate=").append(okRate);
        sb.append(", result=").append(result);
        sb.append(", createTime=").append(createTime);
        sb.append(", testTime=").append(testTime);
        sb.append("]");
        return sb.toString();
    }
}