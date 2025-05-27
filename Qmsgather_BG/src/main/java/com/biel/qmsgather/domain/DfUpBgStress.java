package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg 应力
 * @TableName df_up_bg_stress
 */
@TableName(value ="df_up_bg_stress")
@Data
public class DfUpBgStress {
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
     * 产品型号
     */
    private String productModel;

    /**
     * 产品颜色
     */
    private String profuctColor;

    /**
     * 工序
     */
    private String process;

    /**
     * 日期
     */
    private String date;

    /**
     * 炉号
     */
    private String heatNumber;

    /**
     * 测试id
     */
    private Integer pointId;

    /**
     * 测试时间
     */
    private String testTime;

    /**
     * cs(MPa)
     */
    private Integer csValue;

    /**
     * csk(MPa)
     */
    private Integer cskValue;

    /**
     * dol(MPa)
     */
    private Integer dolValue;

    /**
     * ct(MPa)
     */
    private Integer ctValue;

    /**
     * doc(um)
     */
    private Integer docValue;

    /**
     * ct/ta
     */
    private Integer cttaDivValue;

    /**
     * ct*ta
     */
    private Integer cttaMulValue;

    /**
     * ct(mm)
     */
    private Integer gt;

    /**
     * 上传时间
     */
    private String updateTime;

    /**
     * 上传人
     */
    private String updateName;

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
        DfUpBgStress other = (DfUpBgStress) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFactory() == null ? other.getFactory() == null : this.getFactory().equals(other.getFactory()))
            && (this.getProductModel() == null ? other.getProductModel() == null : this.getProductModel().equals(other.getProductModel()))
            && (this.getProfuctColor() == null ? other.getProfuctColor() == null : this.getProfuctColor().equals(other.getProfuctColor()))
            && (this.getProcess() == null ? other.getProcess() == null : this.getProcess().equals(other.getProcess()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getHeatNumber() == null ? other.getHeatNumber() == null : this.getHeatNumber().equals(other.getHeatNumber()))
            && (this.getPointId() == null ? other.getPointId() == null : this.getPointId().equals(other.getPointId()))
            && (this.getTestTime() == null ? other.getTestTime() == null : this.getTestTime().equals(other.getTestTime()))
            && (this.getCsValue() == null ? other.getCsValue() == null : this.getCsValue().equals(other.getCsValue()))
            && (this.getCskValue() == null ? other.getCskValue() == null : this.getCskValue().equals(other.getCskValue()))
            && (this.getDolValue() == null ? other.getDolValue() == null : this.getDolValue().equals(other.getDolValue()))
            && (this.getCtValue() == null ? other.getCtValue() == null : this.getCtValue().equals(other.getCtValue()))
            && (this.getDocValue() == null ? other.getDocValue() == null : this.getDocValue().equals(other.getDocValue()))
            && (this.getCttaDivValue() == null ? other.getCttaDivValue() == null : this.getCttaDivValue().equals(other.getCttaDivValue()))
            && (this.getCttaMulValue() == null ? other.getCttaMulValue() == null : this.getCttaMulValue().equals(other.getCttaMulValue()))
            && (this.getGt() == null ? other.getGt() == null : this.getGt().equals(other.getGt()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateName() == null ? other.getUpdateName() == null : this.getUpdateName().equals(other.getUpdateName()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFactory() == null) ? 0 : getFactory().hashCode());
        result = prime * result + ((getProductModel() == null) ? 0 : getProductModel().hashCode());
        result = prime * result + ((getProfuctColor() == null) ? 0 : getProfuctColor().hashCode());
        result = prime * result + ((getProcess() == null) ? 0 : getProcess().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getHeatNumber() == null) ? 0 : getHeatNumber().hashCode());
        result = prime * result + ((getPointId() == null) ? 0 : getPointId().hashCode());
        result = prime * result + ((getTestTime() == null) ? 0 : getTestTime().hashCode());
        result = prime * result + ((getCsValue() == null) ? 0 : getCsValue().hashCode());
        result = prime * result + ((getCskValue() == null) ? 0 : getCskValue().hashCode());
        result = prime * result + ((getDolValue() == null) ? 0 : getDolValue().hashCode());
        result = prime * result + ((getCtValue() == null) ? 0 : getCtValue().hashCode());
        result = prime * result + ((getDocValue() == null) ? 0 : getDocValue().hashCode());
        result = prime * result + ((getCttaDivValue() == null) ? 0 : getCttaDivValue().hashCode());
        result = prime * result + ((getCttaMulValue() == null) ? 0 : getCttaMulValue().hashCode());
        result = prime * result + ((getGt() == null) ? 0 : getGt().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateName() == null) ? 0 : getUpdateName().hashCode());
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
        sb.append(", productModel=").append(productModel);
        sb.append(", profuctColor=").append(profuctColor);
        sb.append(", process=").append(process);
        sb.append(", date=").append(date);
        sb.append(", heatNumber=").append(heatNumber);
        sb.append(", pointId=").append(pointId);
        sb.append(", testTime=").append(testTime);
        sb.append(", csValue=").append(csValue);
        sb.append(", cskValue=").append(cskValue);
        sb.append(", dolValue=").append(dolValue);
        sb.append(", ctValue=").append(ctValue);
        sb.append(", docValue=").append(docValue);
        sb.append(", cttaDivValue=").append(cttaDivValue);
        sb.append(", cttaMulValue=").append(cttaMulValue);
        sb.append(", gt=").append(gt);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateName=").append(updateName);
        sb.append(", batchId=").append(batchId);
        sb.append("]");
        return sb.toString();
    }
}