package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：入库任务信息表实体类
 * 创建人：张亮亮
 * 创建时间：2017-06-10
 * 修改人：
 * 修改时间：
 */
public class RkTkInfo {
    private int ruid;
    private String guid;
    private String rkType; //入库类型 00#产成品入库 02#原材料入库 03#生产退料 04#无单入库-生产入库 05#无单入库-返工入库 06#移库入库
    private String rkCode;
    private String assCode;
    private String assCodeGd;
    private String assSource;
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
    private String submitStore;

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

    public String getRkType() {
        return rkType;
    }

    public void setRkType(String rkType) {
        this.rkType = rkType;
    }

    public String getRkCode() {
        return rkCode;
    }

    public void setRkCode(String rkCode) {
        this.rkCode = rkCode;
    }

    public String getAssCode() {
        return assCode;
    }

    public void setAssCode(String assCode) {
        this.assCode = assCode;
    }

    public String getAssCodeGd() {
        return assCodeGd;
    }

    public void setAssCodeGd(String assCodeGd) {
        this.assCodeGd = assCodeGd;
    }

    public String getAssSource() {
        return assSource;
    }

    public void setAssSource(String assSource) {
        this.assSource = assSource;
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

    public String getSubmitStore() {
        return submitStore;
    }

    public void setSubmitStore(String submitStore) {
        this.submitStore = submitStore;
    }
}
