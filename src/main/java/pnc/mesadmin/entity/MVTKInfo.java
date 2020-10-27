package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * Created by zhangliangliang on 2017/11/2.
 */
public class MVTKInfo {
    private int ruid;
    private String guid;
    private String mVCode;
    private String exStatus;
    private String creator;
    private Date createTime;
    private String execor;
    private Date execTime;
    private String cancelor;
    private Date cancelTime;
    private String finishor;
    private Date finishTime;
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

    public String getmVCode() {
        return mVCode;
    }

    public void setmVCode(String mVCode) {
        this.mVCode = mVCode;
    }

    public String getExStatus() {
        return exStatus;
    }

    public void setExStatus(String exStatus) {
        this.exStatus = exStatus;
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

    public String getExecor() {
        return execor;
    }

    public void setExecor(String execor) {
        this.execor = execor;
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public String getCancelor() {
        return cancelor;
    }

    public void setCancelor(String cancelor) {
        this.cancelor = cancelor;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getFinishor() {
        return finishor;
    }

    public void setFinishor(String finishor) {
        this.finishor = finishor;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
