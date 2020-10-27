package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by xfxi on 2017/8/21.
 */
public class ScanPVInfo {
    private int ruid;
    private String guid;
    private String roleGd;
    private String opertMFlag;
    private String pVFlag;
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

    public String getRoleGd() {
        return roleGd;
    }

    public void setRoleGd(String roleGd) {
        this.roleGd = roleGd;
    }

    public String getOpertMFlag() {
        return opertMFlag;
    }

    public void setOpertMFlag(String opertMFlag) {
        this.opertMFlag = opertMFlag;
    }

    public String getpVFlag() {
        return pVFlag;
    }

    public void setpVFlag(String pVFlag) {
        this.pVFlag = pVFlag;
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
