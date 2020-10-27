package pnc.mesadmin.dto.SaveTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 13:09
 * @Description:
 */
public class SaveTaskInfoReq00TaskDetail implements Serializable {
    @JsonProperty("TaskItemName")
    private String TaskItemName;

    @JsonProperty("SureType")
    private String SureType;

    @JsonProperty("MinExCount")
    private int MinExCount;

    @JsonProperty("MaxExCount")
    private int MaxExCount;


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
    public int getMinExCount() {
        return MinExCount;
    }
    @JsonIgnore
    public void setMinExCount(int minExCount) {
        MinExCount = minExCount;
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
