package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术（上海）有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：不良原因明细信息表实体类
 * 创建时间：2018-09-11
 * 修改人：
 * 修改时间：
 */
public class BadDInfo {
    private int ruid;
    private String guid;
    private String badGd;
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

    public String getBadGd() {
        return badGd;
    }

    public void setBadGd(String badGd) {
        this.badGd = badGd;
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
