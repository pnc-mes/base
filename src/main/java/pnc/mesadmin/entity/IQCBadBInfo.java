package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by PNC on 2017/6/10.
 */
public class IQCBadBInfo {
    private int ruid;
    private String guid;
    private String iqcGd;
    private String reaCode;
    private String reaDes;
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

    public String getIqcGd() {
        return iqcGd;
    }

    public void setIqcGd(String iqcGd) {
        this.iqcGd = iqcGd;
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