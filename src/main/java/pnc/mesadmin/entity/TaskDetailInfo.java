package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/8/3 09:10
 * @Description:
 */
public class TaskDetailInfo {
    private int ruid;
    private String guid;
    private String taskGd;
    private String taskItemName;
    private String sureType;
    private int MaxExCount;
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

    public String getTaskGd() {
        return taskGd;
    }

    public void setTaskGd(String taskGd) {
        this.taskGd = taskGd;
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
        return MaxExCount;
    }

    public void setMaxExCount(int maxExCount) {
        MaxExCount = maxExCount;
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
