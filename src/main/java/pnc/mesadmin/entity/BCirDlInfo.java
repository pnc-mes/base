package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：生产流转信息日志表实体类
 * 创建人：潘俊峰
 * 创建时间：2017-06-05
 * 修改人：
 * 修改时间：
 */
public class BCirDlInfo {
    private int ruid;
    private String guid;
    private String woBWGd;
    private String batch;
    private String sLineGd;
    private String sSpecVerGd;
    private String sSpecVerName;
    private String sOpertGd;
    private String sOptName;
    private String tLineGd;
    private String tSpecVerGd;
    private String tSpecName;
    private String tOpertGd;
    private String tOptName;
    private String flowFlag;
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

    public String getWoBWGd() {
        return woBWGd;
    }

    public void setWoBWGd(String woBWGd) {
        this.woBWGd = woBWGd;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getsLineGd() {
        return sLineGd;
    }

    public void setsLineGd(String sLineGd) {
        this.sLineGd = sLineGd;
    }

    public String getsSpecVerGd() {
        return sSpecVerGd;
    }

    public void setsSpecVerGd(String sSpecVerGd) {
        this.sSpecVerGd = sSpecVerGd;
    }

    public String getsSpecVerName() {
        return sSpecVerName;
    }

    public void setsSpecVerName(String sSpecVerName) {
        this.sSpecVerName = sSpecVerName;
    }

    public String getsOpertGd() {
        return sOpertGd;
    }

    public void setsOpertGd(String sOpertGd) {
        this.sOpertGd = sOpertGd;
    }

    public String getsOptName() {
        return sOptName;
    }

    public void setsOptName(String sOptName) {
        this.sOptName = sOptName;
    }

    public String gettLineGd() {
        return tLineGd;
    }

    public void settLineGd(String tLineGd) {
        this.tLineGd = tLineGd;
    }

    public String gettSpecVerGd() {
        return tSpecVerGd;
    }

    public void settSpecVerGd(String tSpecVerGd) {
        this.tSpecVerGd = tSpecVerGd;
    }

    public String gettSpecName() {
        return tSpecName;
    }

    public void settSpecName(String tSpecName) {
        this.tSpecName = tSpecName;
    }

    public String gettOpertGd() {
        return tOpertGd;
    }

    public void settOpertGd(String tOpertGd) {
        this.tOpertGd = tOpertGd;
    }

    public String gettOptName() {
        return tOptName;
    }

    public void settOptName(String tOptName) {
        this.tOptName = tOptName;
    }

    public String getFlowFlag() {
        return flowFlag;
    }

    public void setFlowFlag(String flowFlag) {
        this.flowFlag = flowFlag;
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
