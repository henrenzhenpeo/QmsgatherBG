package com.biel.qmsgather.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 达音笔
 * @TableName df_up_bg_dayinpen
 */
@TableName(value ="df_up_bg_dayinpen")
@Data
public class DfUpBgDayinpen {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工厂
     */
    @Excel(name = "工厂")
    private String factory;

    /**
     * 项目
     */
    @Excel(name = "项目")
    private String project;

    /**
     * 工序
     */
    @Excel(name = "工序")
    private String process;

    /**
     * 颜色
     */
    @Excel(name = "颜色")
    private String color;

    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    /**
     * 日期
     */
    @Excel(name = "日期")
    private String date;

    /**
     * 达音笔#30/#32
     */
    @Excel(name = "达音笔#30/#32")
    private Integer dayinpenId;

    /**
     * 油墨面结果（OK，NG）
     */
    @Excel(name = "油墨面结果")
    private String inkSideResult;

    /**
     * 凹槽弧边结果（OK，NG）
     */
    @Excel(name = "凹槽弧边结果")
    private String cavitySplineResult;

    /**
     * 上传人
     */
    private String uploadName;

    /**
     * 上传时间
     */
    @Excel(name = "上传时间")
    private String uploadTime;

    /**
     * 批次号
     */
    @Excel(name = "批次号")
    private String batchId;

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
        DfUpBgDayinpen other = (DfUpBgDayinpen) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFactory() == null ? other.getFactory() == null : this.getFactory().equals(other.getFactory()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getStage() == null ? other.getStage() == null : this.getStage().equals(other.getStage()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getDayinpenId() == null ? other.getDayinpenId() == null : this.getDayinpenId().equals(other.getDayinpenId()))
            && (this.getInkSideResult() == null ? other.getInkSideResult() == null : this.getInkSideResult().equals(other.getInkSideResult()))
            && (this.getCavitySplineResult() == null ? other.getCavitySplineResult() == null : this.getCavitySplineResult().equals(other.getCavitySplineResult()))
            && (this.getUploadName() == null ? other.getUploadName() == null : this.getUploadName().equals(other.getUploadName()))
            && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactory() == null) ? 0 : getFactory().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getStage() == null) ? 0 : getStage().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getDayinpenId() == null) ? 0 : getDayinpenId().hashCode());
        result = prime * result + ((getInkSideResult() == null) ? 0 : getInkSideResult().hashCode());
        result = prime * result + ((getCavitySplineResult() == null) ? 0 : getCavitySplineResult().hashCode());
        result = prime * result + ((getUploadName() == null) ? 0 : getUploadName().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        result = prime * result + ((getBatchId() == null) ? 0 : getBatchId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", factory=").append(factory);
        sb.append(", project=").append(project);
        sb.append(", color=").append(color);
        sb.append(", stage=").append(stage);
        sb.append(", status=").append(status);
        sb.append(", date=").append(date);
        sb.append(", dayinpenId=").append(dayinpenId);
        sb.append(", inkSideResult=").append(inkSideResult);
        sb.append(", cavitySplineResult=").append(cavitySplineResult);
        sb.append(", uploadName=").append(uploadName);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", batchId=").append(batchId);
        sb.append("]");
        return sb.toString();
    }
}