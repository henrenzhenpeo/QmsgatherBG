package com.biel.qmsgather.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg油墨厚度
 * @TableName df_up_bg_ink_thickness2
 */
@TableName(value ="df_up_bg_ink_thickness2")
@Data
public class DfUpBgInkThickness2 {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工序（丝印/移印）
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
     * 测试时间（YYYY-MM-DD）
     */
    private String testDate;

    /**
     * 名称点位（点位1，点位2，点位3，平台）
     */
    private String namePoint;

    /**
     * 镀膜层数-log
     */
    private String coatingLayersLog;

    /**
     * log测试时间（hh:mm）
     */
    private String testTimeLog;

    /**
     * 真值-log
     */
    private String trueValueLog;

    /**
     * 状态-log（首检，巡检，换网版，待料，停机）
     */
    private String stateLog;

    /**
     * 机台-log
     */
    private String machineCodeLog;

    /**
     * 镀膜层数-一层
     */
    private String coatingLayersOne;

    /**
     * 一层测试时间
     */
    private String testTimeOne;

    /**
     * 一层真值
     */
    private String trueValueOne;

    /**
     * 一层状态
     */
    private String stateOne;

    /**
     * 一层机台号
     */
    private String machineCodeOne;

    /**
     * 镀膜层数-二层
     */
    private String coatingLayersTwo;

    /**
     * 测试时间-二层
     */
    private String testTimeTwo;

    /**
     * 二层真值
     */
    private String trueValueTwo;

    /**
     * 二层状态
     */
    private String stateTwo;

    /**
     * 二层机台号
     */
    private String machineCodeTwo;

    /**
     * 镀膜层数-三层
     */
    private String coatingLayersThree;

    /**
     * 测试时间-三层
     */
    private String testTimeThree;

    /**
     * 三层真值
     */
    private String trueValueThree;

    /**
     * 三层状态
     */
    private String stateThree;

    /**
     * 三层机台号
     */
    private String machineCodeThree;

    /**
     * 镀膜层数-四层
     */
    private String coatingLayersFour;

    /**
     * 测试时间-四层
     */
    private String testTimeFour;

    /**
     * 四层真值
     */
    private String trueValueFour;

    /**
     * 四层状态
     */
    private String stateFour;

    /**
     * 四层机台号
     */
    private String machineCodeFour;

    /**
     * 镀膜层数-五层
     */
    private String coatingLayersFive;

    /**
     * 测试时间-五层
     */
    private String testTimeFive;

    /**
     * 五层真值
     */
    private String trueValueFive;

    /**
     * 五层状态
     */
    private String stateFive;

    /**
     * 五层机台号
     */
    private String machineCodeFive;

    /**
     * 镀膜层数-六层
     */
    private String coatingLayersSix;

    /**
     * 测试时间-六层
     */
    private String testTimeSix;

    /**
     * 六层真值
     */
    private String trueValueSix;

    /**
     * 六层状态
     */
    private String stateSix;

    /**
     * 六层机台号
     */
    private String machineCodeSix;

    /**
     * 镀膜层数-光油
     */
    private String coatingLayersVarnish;

    /**
     * 测试时间-光油
     */
    private String testTimeVarnish;

    /**
     * 光油真值
     */
    private String trueValueVarnish;

    /**
     * 光油状态
     */
    private String stateVarnish;

    /**
     * 光油机台号
     */
    private String machineCodeVarnish;

    /**
     * 镀膜层数-七层
     */
    private String coatingLayersSeven;

    /**
     * 测试时间-七层
     */
    private String testTimeSeven;

    /**
     * 七层真值
     */
    private String trueValueSeven;

    /**
     * 七层状态
     */
    private String stateSeven;

    /**
     * 七层机台号
     */
    private String machineCodeSeven;

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
        DfUpBgInkThickness2 other = (DfUpBgInkThickness2) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProcess() == null ? other.getProcess() == null : this.getProcess().equals(other.getProcess()))
            && (this.getFactory() == null ? other.getFactory() == null : this.getFactory().equals(other.getFactory()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getColor() == null ? other.getColor() == null : this.getColor().equals(other.getColor()))
            && (this.getStage() == null ? other.getStage() == null : this.getStage().equals(other.getStage()))
            && (this.getTestDate() == null ? other.getTestDate() == null : this.getTestDate().equals(other.getTestDate()))
            && (this.getNamePoint() == null ? other.getNamePoint() == null : this.getNamePoint().equals(other.getNamePoint()))
            && (this.getCoatingLayersLog() == null ? other.getCoatingLayersLog() == null : this.getCoatingLayersLog().equals(other.getCoatingLayersLog()))
            && (this.getTestTimeLog() == null ? other.getTestTimeLog() == null : this.getTestTimeLog().equals(other.getTestTimeLog()))
            && (this.getTrueValueLog() == null ? other.getTrueValueLog() == null : this.getTrueValueLog().equals(other.getTrueValueLog()))
            && (this.getStateLog() == null ? other.getStateLog() == null : this.getStateLog().equals(other.getStateLog()))
            && (this.getMachineCodeLog() == null ? other.getMachineCodeLog() == null : this.getMachineCodeLog().equals(other.getMachineCodeLog()))
            && (this.getCoatingLayersOne() == null ? other.getCoatingLayersOne() == null : this.getCoatingLayersOne().equals(other.getCoatingLayersOne()))
            && (this.getTestTimeOne() == null ? other.getTestTimeOne() == null : this.getTestTimeOne().equals(other.getTestTimeOne()))
            && (this.getTrueValueOne() == null ? other.getTrueValueOne() == null : this.getTrueValueOne().equals(other.getTrueValueOne()))
            && (this.getStateOne() == null ? other.getStateOne() == null : this.getStateOne().equals(other.getStateOne()))
            && (this.getMachineCodeOne() == null ? other.getMachineCodeOne() == null : this.getMachineCodeOne().equals(other.getMachineCodeOne()))
            && (this.getCoatingLayersTwo() == null ? other.getCoatingLayersTwo() == null : this.getCoatingLayersTwo().equals(other.getCoatingLayersTwo()))
            && (this.getTestTimeTwo() == null ? other.getTestTimeTwo() == null : this.getTestTimeTwo().equals(other.getTestTimeTwo()))
            && (this.getTrueValueTwo() == null ? other.getTrueValueTwo() == null : this.getTrueValueTwo().equals(other.getTrueValueTwo()))
            && (this.getStateTwo() == null ? other.getStateTwo() == null : this.getStateTwo().equals(other.getStateTwo()))
            && (this.getMachineCodeTwo() == null ? other.getMachineCodeTwo() == null : this.getMachineCodeTwo().equals(other.getMachineCodeTwo()))
            && (this.getCoatingLayersThree() == null ? other.getCoatingLayersThree() == null : this.getCoatingLayersThree().equals(other.getCoatingLayersThree()))
            && (this.getTestTimeThree() == null ? other.getTestTimeThree() == null : this.getTestTimeThree().equals(other.getTestTimeThree()))
            && (this.getTrueValueThree() == null ? other.getTrueValueThree() == null : this.getTrueValueThree().equals(other.getTrueValueThree()))
            && (this.getStateThree() == null ? other.getStateThree() == null : this.getStateThree().equals(other.getStateThree()))
            && (this.getMachineCodeThree() == null ? other.getMachineCodeThree() == null : this.getMachineCodeThree().equals(other.getMachineCodeThree()))
            && (this.getCoatingLayersFour() == null ? other.getCoatingLayersFour() == null : this.getCoatingLayersFour().equals(other.getCoatingLayersFour()))
            && (this.getTestTimeFour() == null ? other.getTestTimeFour() == null : this.getTestTimeFour().equals(other.getTestTimeFour()))
            && (this.getTrueValueFour() == null ? other.getTrueValueFour() == null : this.getTrueValueFour().equals(other.getTrueValueFour()))
            && (this.getStateFour() == null ? other.getStateFour() == null : this.getStateFour().equals(other.getStateFour()))
            && (this.getMachineCodeFour() == null ? other.getMachineCodeFour() == null : this.getMachineCodeFour().equals(other.getMachineCodeFour()))
            && (this.getCoatingLayersFive() == null ? other.getCoatingLayersFive() == null : this.getCoatingLayersFive().equals(other.getCoatingLayersFive()))
            && (this.getTestTimeFive() == null ? other.getTestTimeFive() == null : this.getTestTimeFive().equals(other.getTestTimeFive()))
            && (this.getTrueValueFive() == null ? other.getTrueValueFive() == null : this.getTrueValueFive().equals(other.getTrueValueFive()))
            && (this.getStateFive() == null ? other.getStateFive() == null : this.getStateFive().equals(other.getStateFive()))
            && (this.getMachineCodeFive() == null ? other.getMachineCodeFive() == null : this.getMachineCodeFive().equals(other.getMachineCodeFive()))
            && (this.getCoatingLayersSix() == null ? other.getCoatingLayersSix() == null : this.getCoatingLayersSix().equals(other.getCoatingLayersSix()))
            && (this.getTestTimeSix() == null ? other.getTestTimeSix() == null : this.getTestTimeSix().equals(other.getTestTimeSix()))
            && (this.getTrueValueSix() == null ? other.getTrueValueSix() == null : this.getTrueValueSix().equals(other.getTrueValueSix()))
            && (this.getStateSix() == null ? other.getStateSix() == null : this.getStateSix().equals(other.getStateSix()))
            && (this.getMachineCodeSix() == null ? other.getMachineCodeSix() == null : this.getMachineCodeSix().equals(other.getMachineCodeSix()))
            && (this.getCoatingLayersVarnish() == null ? other.getCoatingLayersVarnish() == null : this.getCoatingLayersVarnish().equals(other.getCoatingLayersVarnish()))
            && (this.getTestTimeVarnish() == null ? other.getTestTimeVarnish() == null : this.getTestTimeVarnish().equals(other.getTestTimeVarnish()))
            && (this.getTrueValueVarnish() == null ? other.getTrueValueVarnish() == null : this.getTrueValueVarnish().equals(other.getTrueValueVarnish()))
            && (this.getStateVarnish() == null ? other.getStateVarnish() == null : this.getStateVarnish().equals(other.getStateVarnish()))
            && (this.getMachineCodeVarnish() == null ? other.getMachineCodeVarnish() == null : this.getMachineCodeVarnish().equals(other.getMachineCodeVarnish()))
            && (this.getCoatingLayersSeven() == null ? other.getCoatingLayersSeven() == null : this.getCoatingLayersSeven().equals(other.getCoatingLayersSeven()))
            && (this.getTestTimeSeven() == null ? other.getTestTimeSeven() == null : this.getTestTimeSeven().equals(other.getTestTimeSeven()))
            && (this.getTrueValueSeven() == null ? other.getTrueValueSeven() == null : this.getTrueValueSeven().equals(other.getTrueValueSeven()))
            && (this.getStateSeven() == null ? other.getStateSeven() == null : this.getStateSeven().equals(other.getStateSeven()))
            && (this.getMachineCodeSeven() == null ? other.getMachineCodeSeven() == null : this.getMachineCodeSeven().equals(other.getMachineCodeSeven()))
            && (this.getUpdateName() == null ? other.getUpdateName() == null : this.getUpdateName().equals(other.getUpdateName()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProcess() == null) ? 0 : getProcess().hashCode());
        result = prime * result + ((getFactory() == null) ? 0 : getFactory().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getColor() == null) ? 0 : getColor().hashCode());
        result = prime * result + ((getStage() == null) ? 0 : getStage().hashCode());
        result = prime * result + ((getTestDate() == null) ? 0 : getTestDate().hashCode());
        result = prime * result + ((getNamePoint() == null) ? 0 : getNamePoint().hashCode());
        result = prime * result + ((getCoatingLayersLog() == null) ? 0 : getCoatingLayersLog().hashCode());
        result = prime * result + ((getTestTimeLog() == null) ? 0 : getTestTimeLog().hashCode());
        result = prime * result + ((getTrueValueLog() == null) ? 0 : getTrueValueLog().hashCode());
        result = prime * result + ((getStateLog() == null) ? 0 : getStateLog().hashCode());
        result = prime * result + ((getMachineCodeLog() == null) ? 0 : getMachineCodeLog().hashCode());
        result = prime * result + ((getCoatingLayersOne() == null) ? 0 : getCoatingLayersOne().hashCode());
        result = prime * result + ((getTestTimeOne() == null) ? 0 : getTestTimeOne().hashCode());
        result = prime * result + ((getTrueValueOne() == null) ? 0 : getTrueValueOne().hashCode());
        result = prime * result + ((getStateOne() == null) ? 0 : getStateOne().hashCode());
        result = prime * result + ((getMachineCodeOne() == null) ? 0 : getMachineCodeOne().hashCode());
        result = prime * result + ((getCoatingLayersTwo() == null) ? 0 : getCoatingLayersTwo().hashCode());
        result = prime * result + ((getTestTimeTwo() == null) ? 0 : getTestTimeTwo().hashCode());
        result = prime * result + ((getTrueValueTwo() == null) ? 0 : getTrueValueTwo().hashCode());
        result = prime * result + ((getStateTwo() == null) ? 0 : getStateTwo().hashCode());
        result = prime * result + ((getMachineCodeTwo() == null) ? 0 : getMachineCodeTwo().hashCode());
        result = prime * result + ((getCoatingLayersThree() == null) ? 0 : getCoatingLayersThree().hashCode());
        result = prime * result + ((getTestTimeThree() == null) ? 0 : getTestTimeThree().hashCode());
        result = prime * result + ((getTrueValueThree() == null) ? 0 : getTrueValueThree().hashCode());
        result = prime * result + ((getStateThree() == null) ? 0 : getStateThree().hashCode());
        result = prime * result + ((getMachineCodeThree() == null) ? 0 : getMachineCodeThree().hashCode());
        result = prime * result + ((getCoatingLayersFour() == null) ? 0 : getCoatingLayersFour().hashCode());
        result = prime * result + ((getTestTimeFour() == null) ? 0 : getTestTimeFour().hashCode());
        result = prime * result + ((getTrueValueFour() == null) ? 0 : getTrueValueFour().hashCode());
        result = prime * result + ((getStateFour() == null) ? 0 : getStateFour().hashCode());
        result = prime * result + ((getMachineCodeFour() == null) ? 0 : getMachineCodeFour().hashCode());
        result = prime * result + ((getCoatingLayersFive() == null) ? 0 : getCoatingLayersFive().hashCode());
        result = prime * result + ((getTestTimeFive() == null) ? 0 : getTestTimeFive().hashCode());
        result = prime * result + ((getTrueValueFive() == null) ? 0 : getTrueValueFive().hashCode());
        result = prime * result + ((getStateFive() == null) ? 0 : getStateFive().hashCode());
        result = prime * result + ((getMachineCodeFive() == null) ? 0 : getMachineCodeFive().hashCode());
        result = prime * result + ((getCoatingLayersSix() == null) ? 0 : getCoatingLayersSix().hashCode());
        result = prime * result + ((getTestTimeSix() == null) ? 0 : getTestTimeSix().hashCode());
        result = prime * result + ((getTrueValueSix() == null) ? 0 : getTrueValueSix().hashCode());
        result = prime * result + ((getStateSix() == null) ? 0 : getStateSix().hashCode());
        result = prime * result + ((getMachineCodeSix() == null) ? 0 : getMachineCodeSix().hashCode());
        result = prime * result + ((getCoatingLayersVarnish() == null) ? 0 : getCoatingLayersVarnish().hashCode());
        result = prime * result + ((getTestTimeVarnish() == null) ? 0 : getTestTimeVarnish().hashCode());
        result = prime * result + ((getTrueValueVarnish() == null) ? 0 : getTrueValueVarnish().hashCode());
        result = prime * result + ((getStateVarnish() == null) ? 0 : getStateVarnish().hashCode());
        result = prime * result + ((getMachineCodeVarnish() == null) ? 0 : getMachineCodeVarnish().hashCode());
        result = prime * result + ((getCoatingLayersSeven() == null) ? 0 : getCoatingLayersSeven().hashCode());
        result = prime * result + ((getTestTimeSeven() == null) ? 0 : getTestTimeSeven().hashCode());
        result = prime * result + ((getTrueValueSeven() == null) ? 0 : getTrueValueSeven().hashCode());
        result = prime * result + ((getStateSeven() == null) ? 0 : getStateSeven().hashCode());
        result = prime * result + ((getMachineCodeSeven() == null) ? 0 : getMachineCodeSeven().hashCode());
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
        sb.append(", process=").append(process);
        sb.append(", factory=").append(factory);
        sb.append(", project=").append(project);
        sb.append(", color=").append(color);
        sb.append(", stage=").append(stage);
        sb.append(", testDate=").append(testDate);
        sb.append(", namePoint=").append(namePoint);
        sb.append(", coatingLayersLog=").append(coatingLayersLog);
        sb.append(", testTimeLog=").append(testTimeLog);
        sb.append(", trueValueLog=").append(trueValueLog);
        sb.append(", stateLog=").append(stateLog);
        sb.append(", machineCodeLog=").append(machineCodeLog);
        sb.append(", coatingLayersOne=").append(coatingLayersOne);
        sb.append(", testTimeOne=").append(testTimeOne);
        sb.append(", trueValueOne=").append(trueValueOne);
        sb.append(", stateOne=").append(stateOne);
        sb.append(", machineCodeOne=").append(machineCodeOne);
        sb.append(", coatingLayersTwo=").append(coatingLayersTwo);
        sb.append(", testTimeTwo=").append(testTimeTwo);
        sb.append(", trueValueTwo=").append(trueValueTwo);
        sb.append(", stateTwo=").append(stateTwo);
        sb.append(", machineCodeTwo=").append(machineCodeTwo);
        sb.append(", coatingLayersThree=").append(coatingLayersThree);
        sb.append(", testTimeThree=").append(testTimeThree);
        sb.append(", trueValueThree=").append(trueValueThree);
        sb.append(", stateThree=").append(stateThree);
        sb.append(", machineCodeThree=").append(machineCodeThree);
        sb.append(", coatingLayersFour=").append(coatingLayersFour);
        sb.append(", testTimeFour=").append(testTimeFour);
        sb.append(", trueValueFour=").append(trueValueFour);
        sb.append(", stateFour=").append(stateFour);
        sb.append(", machineCodeFour=").append(machineCodeFour);
        sb.append(", coatingLayersFive=").append(coatingLayersFive);
        sb.append(", testTimeFive=").append(testTimeFive);
        sb.append(", trueValueFive=").append(trueValueFive);
        sb.append(", stateFive=").append(stateFive);
        sb.append(", machineCodeFive=").append(machineCodeFive);
        sb.append(", coatingLayersSix=").append(coatingLayersSix);
        sb.append(", testTimeSix=").append(testTimeSix);
        sb.append(", trueValueSix=").append(trueValueSix);
        sb.append(", stateSix=").append(stateSix);
        sb.append(", machineCodeSix=").append(machineCodeSix);
        sb.append(", coatingLayersVarnish=").append(coatingLayersVarnish);
        sb.append(", testTimeVarnish=").append(testTimeVarnish);
        sb.append(", trueValueVarnish=").append(trueValueVarnish);
        sb.append(", stateVarnish=").append(stateVarnish);
        sb.append(", machineCodeVarnish=").append(machineCodeVarnish);
        sb.append(", coatingLayersSeven=").append(coatingLayersSeven);
        sb.append(", testTimeSeven=").append(testTimeSeven);
        sb.append(", trueValueSeven=").append(trueValueSeven);
        sb.append(", stateSeven=").append(stateSeven);
        sb.append(", machineCodeSeven=").append(machineCodeSeven);
        sb.append(", updateName=").append(updateName);
        sb.append(", batchId=").append(batchId);
        sb.append("]");
        return sb.toString();
    }
}