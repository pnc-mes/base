package pnc.mesadmin.entity.common;

import java.util.Date;

/**
 * Description: mesadmin
 * Created By panjunfeng on 2018/11/7
 */
public class PTELBadInfo {
    private int ruid;
    private String guid;
    private String eLGd;
    private String source;
    private String badCode;
    private String badName;
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

    public String geteLGd() {
        return eLGd;
    }

    public void seteLGd(String eLGd) {
        this.eLGd = eLGd;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBadCode() {
        return badCode;
    }

    public void setBadCode(String badCode) {
        this.badCode = badCode;
    }

    public String getBadName() {
        return badName;
    }

    public void setBadName(String badName) {
        this.badName = badName;
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
