package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：保养计划任务执行明细信息表
 * 创建时间：2018-09-11
 * 修改人：
 * 修改时间：
 */
public class DoPMDtlInfo {
    private int ruid;
    private String guid;
    private String DoPMGd;
    private String TaskItemName;
    private String SureType;
    private String ExecResult00;
    private String ExecResult02;
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

    public String getDoPMGd() {
        return DoPMGd;
    }

    public void setDoPMGd(String doPMGd) {
        DoPMGd = doPMGd;
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

    public String getExecResult00() {
        return ExecResult00;
    }

    public void setExecResult00(String execResult00) {
        ExecResult00 = execResult00;
    }

    public String getExecResult02() {
        return ExecResult02;
    }

    public void setExecResult02(String execResult02) {
        ExecResult02 = execResult02;
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
