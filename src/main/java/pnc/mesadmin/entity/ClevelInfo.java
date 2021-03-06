package pnc.mesadmin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by PNC on 2019/3/22.
 */
public class ClevelInfo implements Serializable {

    private int ruid;
    private String guid;
    private String CheckLevelCode;
    private String CheckLevelName;
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

    public String getCheckLevelCode() {
        return CheckLevelCode;
    }

    public void setCheckLevelCode(String checkLevelCode) {
        CheckLevelCode = checkLevelCode;
    }

    public String getCheckLevelName() {
        return CheckLevelName;
    }

    public void setCheckLevelName(String checkLevelName) {
        CheckLevelName = checkLevelName;
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
