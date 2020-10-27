package pnc.mesadmin.dto.SaveTaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/6 08:25
 * @Description:
 */
public class SaveTaskInfoReq01 {
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
