package pnc.mesadmin.dto.GetAllDotaskInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @program: mesadmin
 * @description: 生成保养/ 点检列表
 * @author: yin.yang
 * @create: 2019-05-09
 **/
public class SaveCreateTaskReqest {
    //任务 ID
    @JsonProperty("TaskRd")
    private Integer TaskRd;
    //任务类型 00#保养 01#点检
    @JsonProperty("TaskType")
    private String TaskType;
    //对象类型 00#设备 01#工具 02#载具
    @JsonProperty("ObjType")
    private String ObjType;
    //执行对象 ID
    @JsonProperty("ObjRd")
    private Integer ObjRd;
    //过期时间
    @JsonProperty("MaTime")
    private String MaTime;

    @JsonIgnore
    public Integer getTaskRd() {
        return TaskRd;
    }

    @JsonIgnore
    public void setTaskRd(Integer taskRd) {
        TaskRd = taskRd;
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
    public String getObjType() {
        return ObjType;
    }

    @JsonIgnore
    public void setObjType(String objType) {
        ObjType = objType;
    }

    @JsonIgnore
    public Integer getObjRd() {
        return ObjRd;
    }

    @JsonIgnore
    public void setObjRd(Integer objRd) {
        ObjRd = objRd;
    }

    @JsonIgnore
    public String getMaTime() {
        return MaTime;
    }

    @JsonIgnore
    public void setMaTime(String maTime) {
        MaTime = maTime;
    }
}
