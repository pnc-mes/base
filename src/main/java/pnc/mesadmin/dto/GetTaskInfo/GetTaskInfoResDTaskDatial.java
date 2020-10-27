package pnc.mesadmin.dto.GetTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/6 13:46
 * @Description:
 */
public class GetTaskInfoResDTaskDatial {
    @JsonProperty("TaskDetailRd")
    private int TaskDetailRd;

    @JsonProperty("TaskItemName")
    private String TaskItemName;

    @JsonProperty("SureType")
    private String SureType;

    @JsonProperty("MaxExCount")
    private int MaxExCount;

    @JsonIgnore
    public int getTaskDetailRd() {
        return TaskDetailRd;
    }
    @JsonIgnore
    public void setTaskDetailRd(int taskDetailRd) {
        TaskDetailRd = taskDetailRd;
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
    public int getMaxExCount() {
        return MaxExCount;
    }
    @JsonIgnore
    public void setMaxExCount(int maxExCount) {
        MaxExCount = maxExCount;
    }
}
