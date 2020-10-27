package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/10 09:30
 * @Description:
 */
public class CheckPlanDtllnfo {
    private int ruid;
    private String guid;
    private String checkPlanGd;
    private String taskGd;
    private String taskName;
    private String taskItemName;
    private String sureType;

    private int maxExCount;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCheckPlanGd() {
        return checkPlanGd;
    }

    public void setCheckPlanGd(String checkPlanGd) {
        this.checkPlanGd = checkPlanGd;
    }

    public String getTaskGd() {
        return taskGd;
    }

    public void setTaskGd(String taskGd) {
        this.taskGd = taskGd;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskItemName() {
        return taskItemName;
    }

    public void setTaskItemName(String taskItemName) {
        this.taskItemName = taskItemName;
    }

    public String getSureType() {
        return sureType;
    }

    public void setSureType(String sureType) {
        this.sureType = sureType;
    }


    public int getMaxExCount() {
        return maxExCount;
    }

    public void setMaxExCount(int maxExCount) {
        this.maxExCount = maxExCount;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyMan() {
        return lastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        this.lastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
