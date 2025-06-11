package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 实验--丝印-油墨密度
 * @TableName df_ort_optical_density
 */
@TableName(value ="df_ort_optical_density")
@Data
public class DfOrtOpticalDensity {
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
     * 序号
     */
    private String no;

    /**
     * ink1值
     */
    private Double ink1;

    /**
     * ink2值
     */
    private Double ink2;

    /**
     * ink3值
     */
    private Double ink3;

    /**
     * ink4值
     */
    private Double ink4;

    /**
     * 检测时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date checkTime;

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
        DfOrtOpticalDensity other = (DfOrtOpticalDensity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getBatch() == null ? other.getBatch() == null : this.getBatch().equals(other.getBatch()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getInk1() == null ? other.getInk1() == null : this.getInk1().equals(other.getInk1()))
            && (this.getInk2() == null ? other.getInk2() == null : this.getInk2().equals(other.getInk2()))
            && (this.getInk3() == null ? other.getInk3() == null : this.getInk3().equals(other.getInk3()))
            && (this.getInk4() == null ? other.getInk4() == null : this.getInk4().equals(other.getInk4()))
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
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getInk1() == null) ? 0 : getInk1().hashCode());
        result = prime * result + ((getInk2() == null) ? 0 : getInk2().hashCode());
        result = prime * result + ((getInk3() == null) ? 0 : getInk3().hashCode());
        result = prime * result + ((getInk4() == null) ? 0 : getInk4().hashCode());
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
        sb.append(", no=").append(no);
        sb.append(", ink1=").append(ink1);
        sb.append(", ink2=").append(ink2);
        sb.append(", ink3=").append(ink3);
        sb.append(", ink4=").append(ink4);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}