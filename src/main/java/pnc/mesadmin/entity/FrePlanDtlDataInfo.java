package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by haozan on 2018/9/7.d
 * 次数保养计划明细信息表实体类
 */
public class FrePlanDtlDataInfo {
    private Integer Ruid;
    private String Guid;
    private String FrePlanGd;
    private String TaskGd;
    private String TaskName;
    private String TaskItemName;
    private String SureType;
/*    private Integer MinExCount;*/
    private Integer MaxExCount;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getFrePlanGd() {
        return FrePlanGd;
    }

    public void setFrePlanGd(String frePlanGd) {
        FrePlanGd = frePlanGd;
    }

    public String getTaskGd() {
        return TaskGd;
    }

    public void setTaskGd(String taskGd) {
        TaskGd = taskGd;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskItemName() {
        return TaskItemName;
    }

    public void setTaskItemName(String taskItemName) {
        TaskItemName = taskItemName;
    }

    public String getSureType() {
        return SureType;
    }

    public void setSureType(String sureType) {
        SureType = sureType;
    }

/*    public Integer getMinExCount() {
        return MinExCount;
    }

    public void setMinExCount(Integer minExCount) {
        MinExCount = minExCount;
    }*/

    public Integer getMaxExCount() {
        return MaxExCount;
    }

    public void setMaxExCount(Integer maxExCount) {
        MaxExCount = maxExCount;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
