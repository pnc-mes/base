package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by PNC on 2017/8/15.
 */
public class DoRcInfo {
    private int ruid;
    private String guid;
    private String batch;
    private String specVerGd;
    private String specName;
    private String devGd;
    private String devName;
    private String reaCode;
    private String reaDes;
    private Integer num;
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

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSpecVerGd() {
        return specVerGd;
    }

    public void setSpecVerGd(String specVerGd) {
        this.specVerGd = specVerGd;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getDevGd() {
        return devGd;
    }

    public void setDevGd(String devGd) {
        this.devGd = devGd;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getReaCode() {
        return reaCode;
    }

    public void setReaCode(String reaCode) {
        this.reaCode = reaCode;
    }

    public String getReaDes() {
        return reaDes;
    }

    public void setReaDes(String reaDes) {
        this.reaDes = reaDes;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
