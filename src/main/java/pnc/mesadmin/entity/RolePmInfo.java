package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by xfxi on 2017/5/10.
 */
public class RolePmInfo {
    private int ruid;
    private String guid;
    private String roleGd;
    private String moduleGd;
    private String resGd;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;
    private String resOptGd;

    public String getResOptGd() {
        return resOptGd;
    }

    public void setResOptGd(String resOptGd) {
        this.resOptGd = resOptGd;
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

    public String getRoleGd() {
        return roleGd;
    }

    public void setRoleGd(String roleGd) {
        this.roleGd = roleGd;
    }

    public String getModuleGd() {
        return moduleGd;
    }

    public void setModuleGd(String moduleGd) {
        this.moduleGd = moduleGd;
    }

    public String getResGd() {
        return resGd;
    }

    public void setResGd(String resGd) {
        this.resGd = resGd;
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
