package pnc.mesadmin.dto.GetTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/6 13:38
 * @Description:
 */
public class GetTaskInfoResD {
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

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("TaskDetailInfo")
    private List<GetTaskInfoResDTaskDatial>  TaskDetailInfo;

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
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
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
    public List<GetTaskInfoResDTaskDatial> getTaskDetailInfo() {
        return TaskDetailInfo;
    }
    @JsonIgnore
    public void setTaskDetailInfo(List<GetTaskInfoResDTaskDatial> taskDetailInfo) {
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
}
