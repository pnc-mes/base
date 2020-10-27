package pnc.mesadmin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PNC on 2017/8/16.
 */
public class SkillGdlInfo implements Serializable {
    private int ruid;
    private String guid;
    private String skillGGd;
    private String skillGd;
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

    public String getSkillGGd() {
        return skillGGd;
    }

    public void setSkillGGd(String skillGGd) {
        this.skillGGd = skillGGd;
    }

    public String getSkillGd() {
        return skillGd;
    }

    public void setSkillGd(String skillGd) {
        this.skillGd = skillGd;
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
