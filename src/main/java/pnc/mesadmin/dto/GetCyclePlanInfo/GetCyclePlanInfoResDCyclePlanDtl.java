package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:31
 * @Description:
 */
public class GetCyclePlanInfoResDCyclePlanDtl implements Serializable {
    @JsonProperty("CyclePlanDtlRd")
    private int CyclePlanDtlRd;
    @JsonProperty("TaskRd")
    private int TaskRd;
    @JsonProperty("TaskName")
    private String TaskName;
    @JsonProperty("TaskItemName")
    private String TaskItemName;
    @JsonProperty("SureType")
    private String SureType;
/*    @JsonProperty("MinExCount")
    private int MinExCount;*/
    @JsonProperty("MaxExCount")
    private int MaxExCount;
    @JsonIgnore
    public int getCyclePlanDtlRd() {
        return CyclePlanDtlRd;
    }
    @JsonIgnore
    public void setCyclePlanDtlRd(int cyclePlanDtlRd) {
        CyclePlanDtlRd = cyclePlanDtlRd;
    }
    @JsonIgnore
    public int getTaskRd() {
        return TaskRd;
    }
    @JsonIgnore
    public void setTaskRd(int taskRd) {
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
    public int getMinExCount() {
        return MinExCount;
    }
    @JsonIgnore
    public void setMinExCount(int minExCount) {
        MinExCount = minExCount;
    }*/
    @JsonIgnore
    public int getMaxExCount() {
        return MaxExCount;
    }
    @JsonIgnore
    public void setMaxExCount(int maxExCount) {
        MaxExCount = maxExCount;
    }
}
