package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg倒角切片
 * @TableName df_up_bg_chamfering_slicing
 */
@TableName(value ="df_up_bg_chamfering_slicing")
@Data
public class DfUpBgChamferingSlicing {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    private String status;

    /**
     * 日期
     */
    private String date;

    /**
     * 测量班别
     */
    private String testClasses;

    /**
     * 测量时间
     */
    private String testTime;

    /**
     * 测量描述
     */
    private String testRemark;

    /**
     * 
     */
    private String testPoint;

    /**
     * FAI_Y
     */
    private String testFaiY;

    /**
     * FAI_X
     */
    private String testFaiX;

    /**
     * 测试图片1
     */
    private String testImg1;

    /**
     * 测试图片2
     */
    private String testImg2;

    /**
     * 测试图片3
     */
    private String testImg3;

    /**
     * 
     */
    private String test1Y;

    /**
     * 
     */
    private String test2Y;

    /**
     * 
     */
    private String test3Y;

    /**
     * 
     */
    private String test1X;

    /**
     * 
     */
    private String test2X;

    /**
     * 
     */
    private String test3X;

    /**
     * 数据上传人
     */
    private String updateName;

    /**
     * 上传时间
     */
    private String updateTime;

    /**
     * 批次号
     */
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
        DfUpBgChamferingSlicing other = (DfUpBgChamferingSlicing) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFactory() == null ? other.getFactory() == null : this.getFactory().equals(other.getFactory()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getStage() == null ? other.getStage() == null : this.getStage().equals(other.getStage()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getTestClasses() == null ? other.getTestClasses() == null : this.getTestClasses().equals(other.getTestClasses()))
            && (this.getTestTime() == null ? other.getTestTime() == null : this.getTestTime().equals(other.getTestTime()))
            && (this.getTestRemark() == null ? other.getTestRemark() == null : this.getTestRemark().equals(other.getTestRemark()))
            && (this.getTestPoint() == null ? other.getTestPoint() == null : this.getTestPoint().equals(other.getTestPoint()))
            && (this.getTestFaiY() == null ? other.getTestFaiY() == null : this.getTestFaiY().equals(other.getTestFaiY()))
            && (this.getTestFaiX() == null ? other.getTestFaiX() == null : this.getTestFaiX().equals(other.getTestFaiX()))
            && (this.getTestImg1() == null ? other.getTestImg1() == null : this.getTestImg1().equals(other.getTestImg1()))
            && (this.getTestImg2() == null ? other.getTestImg2() == null : this.getTestImg2().equals(other.getTestImg2()))
            && (this.getTestImg3() == null ? other.getTestImg3() == null : this.getTestImg3().equals(other.getTestImg3()))
            && (this.getTest1Y() == null ? other.getTest1Y() == null : this.getTest1Y().equals(other.getTest1Y()))
            && (this.getTest2Y() == null ? other.getTest2Y() == null : this.getTest2Y().equals(other.getTest2Y()))
            && (this.getTest3Y() == null ? other.getTest3Y() == null : this.getTest3Y().equals(other.getTest3Y()))
            && (this.getTest1X() == null ? other.getTest1X() == null : this.getTest1X().equals(other.getTest1X()))
            && (this.getTest2X() == null ? other.getTest2X() == null : this.getTest2X().equals(other.getTest2X()))
            && (this.getTest3X() == null ? other.getTest3X() == null : this.getTest3X().equals(other.getTest3X()))
            && (this.getUpdateName() == null ? other.getUpdateName() == null : this.getUpdateName().equals(other.getUpdateName()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
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
        result = prime * result + ((getTestClasses() == null) ? 0 : getTestClasses().hashCode());
        result = prime * result + ((getTestTime() == null) ? 0 : getTestTime().hashCode());
        result = prime * result + ((getTestRemark() == null) ? 0 : getTestRemark().hashCode());
        result = prime * result + ((getTestPoint() == null) ? 0 : getTestPoint().hashCode());
        result = prime * result + ((getTestFaiY() == null) ? 0 : getTestFaiY().hashCode());
        result = prime * result + ((getTestFaiX() == null) ? 0 : getTestFaiX().hashCode());
        result = prime * result + ((getTestImg1() == null) ? 0 : getTestImg1().hashCode());
        result = prime * result + ((getTestImg2() == null) ? 0 : getTestImg2().hashCode());
        result = prime * result + ((getTestImg3() == null) ? 0 : getTestImg3().hashCode());
        result = prime * result + ((getTest1Y() == null) ? 0 : getTest1Y().hashCode());
        result = prime * result + ((getTest2Y() == null) ? 0 : getTest2Y().hashCode());
        result = prime * result + ((getTest3Y() == null) ? 0 : getTest3Y().hashCode());
        result = prime * result + ((getTest1X() == null) ? 0 : getTest1X().hashCode());
        result = prime * result + ((getTest2X() == null) ? 0 : getTest2X().hashCode());
        result = prime * result + ((getTest3X() == null) ? 0 : getTest3X().hashCode());
        result = prime * result + ((getUpdateName() == null) ? 0 : getUpdateName().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
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
        sb.append(", testClasses=").append(testClasses);
        sb.append(", testTime=").append(testTime);
        sb.append(", testRemark=").append(testRemark);
        sb.append(", testPoint=").append(testPoint);
        sb.append(", testFaiY=").append(testFaiY);
        sb.append(", testFaiX=").append(testFaiX);
        sb.append(", testImg1=").append(testImg1);
        sb.append(", testImg2=").append(testImg2);
        sb.append(", testImg3=").append(testImg3);
        sb.append(", test1Y=").append(test1Y);
        sb.append(", test2Y=").append(test2Y);
        sb.append(", test3Y=").append(test3Y);
        sb.append(", test1X=").append(test1X);
        sb.append(", test2X=").append(test2X);
        sb.append(", test3X=").append(test3X);
        sb.append(", updateName=").append(updateName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", batchId=").append(batchId);
        sb.append("]");
        return sb.toString();
    }
}