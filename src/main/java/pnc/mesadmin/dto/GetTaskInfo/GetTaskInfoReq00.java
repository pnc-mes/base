package pnc.mesadmin.dto.GetTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/6 13:38
 * @Description:
 */
public class GetTaskInfoReq00 {
    @JsonProperty("TaskRd")
    private int TaskRd;
    @JsonIgnore
    public int getTaskRd() {
        return TaskRd;
    }
    @JsonIgnore
    public void setTaskRd(int taskRd) {
        TaskRd = taskRd;
    }
}
