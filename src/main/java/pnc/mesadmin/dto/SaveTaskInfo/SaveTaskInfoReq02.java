package pnc.mesadmin.dto.SaveTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 13:08
 * @Description:
 */
public class SaveTaskInfoReq02 implements Serializable {
    @JsonProperty("TaskRd")
    private int TaskRd;

    @JsonProperty("TaskName")
    private String TaskName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("TaskType")
    private String TaskType;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("TaskDetailInfo")
    private List<SaveTaskInfoReq02TaskDetail> TaskDetailInfo;
    @JsonIgnore
    public String getTaskName() {
        return TaskName;
    }
    @JsonIgnore
    public void setTaskName(String taskName) {
        TaskName = taskName;
    }
    @JsonIgnore
    public String getTaskType() {
        return TaskType;
    }
    @JsonIgnore
    public void setTaskType(String taskType) {
        TaskType = taskType;
    }

    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }

    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
    @JsonIgnore
    public List<SaveTaskInfoReq02TaskDetail> getTaskDetailInfo() {
        return TaskDetailInfo;
    }
    @JsonIgnore
    public void setTaskDetailInfo(List<SaveTaskInfoReq02TaskDetail> taskDetailInfo) {
        TaskDetailInfo = taskDetailInfo;
    }
    @JsonIgnore
    public int getTaskRd() {
        return TaskRd;
    }
    @JsonIgnore
    public void setTaskRd(int taskRd) {
        TaskRd = taskRd;
    }
}
