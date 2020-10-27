package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoReq02CyclePlanDtl implements Serializable {
    @JsonProperty("CheckPlanDtlRd")
    private int CheckPlanDtlRd;
    @JsonProperty("TaskRd")
    private int TaskRd;
/*    @JsonProperty("MinExCount")
    private int MinExCount;*/
    @JsonProperty("MaxExCount")
    private int MaxExCount;
    @JsonProperty("TaskName")
    private String TaskName;
    @JsonProperty("TaskItemName")
    private String TaskItemName;
    @JsonProperty("SureType")
    private String SureType;
    @JsonIgnore
    public int getTaskRd() {
        return TaskRd;
    }
    @JsonIgnore
    public void setTaskRd(int taskRd) {
        TaskRd = taskRd;
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
    @JsonIgnore
    public int getCheckPlanDtlRd() {
        return CheckPlanDtlRd;
    }
    @JsonIgnore
    public void setCheckPlanDtlRd(int checkPlanDtlRd) {
        CheckPlanDtlRd = checkPlanDtlRd;
    }
}
