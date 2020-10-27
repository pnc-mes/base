package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：出库单实体类
 * 创建人：张亮亮
 * 创建时间：2017-8-7
 * 修改人：
 * 修改时间：
 */
public class CkTkInfo {
    private int ruid;
    private String guid;
    private String ckType; // 00#领料出库01#销售出库02#其他出库
    private String ckCode;
    private String assCode;
    private String exStatus;
   /* private String submitStore;*/
    private String creator;
    private Date createTime;
    private String execor;
    private Date execTime;
    private String cancelor;
    private Date cancelTime;
    private Date finishTime;
    private String finishor;
    private String remark;
    private String assSource;
    private String assCodeGd;

    public String getAssCodeGd() {
        return assCodeGd;
    }

    public void setAssCodeGd(String assCodeGd) {
        this.assCodeGd = assCodeGd;
    }

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

    public String getCkType() {
        return ckType;
    }

    public void setCkType(String ckType) {
        this.ckType = ckType;
    }

    public String getCkCode() {
        return ckCode;
    }

    public void setCkCode(String ckCode) {
        this.ckCode = ckCode;
    }

    public String getAssCode() {
        return assCode;
    }

    public void setAssCode(String assCode) {
        this.assCode = assCode;
    }

    public String getExStatus() {
        return exStatus;
    }

    public void setExStatus(String exStatus) {
        this.exStatus = exStatus;
    }

   /* public String getSubmitStore() {
        return submitStore;
    }

    public void setSubmitStore(String submitStore) {
        this.submitStore = submitStore;
    }*/

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

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getFinishor() {
        return finishor;
    }

    public void setFinishor(String finishor) {
        this.finishor = finishor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAssSource() {
        return assSource;
    }

    public void setAssSource(String assSource) {
        this.assSource = assSource;
    }
}
