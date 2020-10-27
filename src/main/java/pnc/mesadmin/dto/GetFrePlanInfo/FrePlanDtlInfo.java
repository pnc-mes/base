package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by haozan on 2018/9/7.
 */
public class FrePlanDtlInfo {
    @JsonProperty("FrePlanDtlRd")
    private Integer FrePlanDtlRd;
    @JsonProperty("TaskRd")
    private Integer TaskRd;
    @JsonProperty("TaskName")
    private String TaskName;
    @JsonProperty("TaskItemName")
    private String TaskItemName;
    @JsonProperty("SureType")
    private String SureType;
/*    @JsonProperty("MinExCount")
    private Integer MinExCount;*/
    @JsonProperty("MaxExCount")
    private Integer MaxExCount;

    @JsonIgnore
    public Integer getFrePlanDtlRd() {
        return FrePlanDtlRd;
    }
    @JsonIgnore
    public void setFrePlanDtlRd(Integer frePlanDtlRd) {
        FrePlanDtlRd = frePlanDtlRd;
    }
    @JsonIgnore
    public Integer getTaskRd() {
        return TaskRd;
    }
    @JsonIgnore
    public void setTaskRd(Integer taskRd) {
        TaskRd = taskRd;
    }
    @JsonIgnore
    public String getTaskName() {
        return TaskName;
    }
    @JsonIgnore
    public void setTaskName(String taskName) {
        TaskName = taskName;
    }
    @JsonIgnore
    public String getTaskItemName() {
        return TaskItemName;
    }
    @JsonIgnore
    public void setTaskItemName(String taskItemName) {
        TaskItemName = taskItemName;
    }
    @JsonIgnore
    public String getSureType() {
        return SureType;
    }
    @JsonIgnore
    public void setSureType(String sureType) {
        SureType = sureType;
    }
/*    @JsonIgnore
    public Integer getMinExCount() {
        return MinExCount;
    }
    @JsonIgnore
    public void setMinExCount(Integer minExCount) {
        MinExCount = minExCount;
    }*/
    @JsonIgnore
    public Integer getMaxExCount() {
        return MaxExCount;
    }
    @JsonIgnore
    public void setMaxExCount(Integer maxExCount) {
        MaxExCount = maxExCount;
    }
}
