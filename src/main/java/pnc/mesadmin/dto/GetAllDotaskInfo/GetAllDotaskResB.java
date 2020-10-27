package pnc.mesadmin.dto.GetAllDotaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class GetAllDotaskResB implements Serializable {
    @JsonProperty("DoTaskRd")
    private Integer DoTaskRd;
    @JsonProperty("DoTaskName")
    private String DoTaskName;
    @JsonProperty("MaTime")
    private String MaTime;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("DoTaskDtlInfo")
    private List<DoTaskDtlInfo> DoTaskDtlInfo;

    public static class DoTaskDtlInfo {
        @JsonProperty("DoTaskDtlRd")
        private Integer DoTaskDtlRd;
        @JsonProperty("TaskItemName")
        private String TaskItemName;
        @JsonProperty("SureType")
        private String SureType;
        @JsonProperty("ExecResult")
        private String ExecResult;

        @JsonIgnore
        public Integer getDoTaskDtlRd() {
            return DoTaskDtlRd;
        }

        @JsonIgnore
        public void setDoTaskDtlRd(Integer doTaskDtlRd) {
            DoTaskDtlRd = doTaskDtlRd;
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
        public String getExecResult() {
            return ExecResult;
        }

        @JsonIgnore
        public void setExecResult(String execResult) {
            ExecResult = execResult;
        }

    }

    @JsonIgnore
    public List<GetAllDotaskResB.DoTaskDtlInfo> getDoTaskDtlInfo() {
        return DoTaskDtlInfo;
    }

    @JsonIgnore
    public void setDoTaskDtlInfo(List<GetAllDotaskResB.DoTaskDtlInfo> doTaskDtlInfo) {
        DoTaskDtlInfo = doTaskDtlInfo;
    }

    @JsonIgnore
    public Integer getDoTaskRd() {
        return DoTaskRd;
    }

    @JsonIgnore
    public void setDoTaskRd(Integer doTaskRd) {
        DoTaskRd = doTaskRd;
    }

    @JsonIgnore
    public String getDoTaskName() {
        return DoTaskName;
    }

    @JsonIgnore
    public void setDoTaskName(String doTaskName) {
        DoTaskName = doTaskName;
    }

    @JsonIgnore
    public String getMaTime() {
        return MaTime;
    }

    @JsonIgnore
    public void setMaTime(String maTime) {
        MaTime = maTime;
    }

    @JsonIgnore
    public String getStatus() {
        return Status;
    }

    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
    }


}
