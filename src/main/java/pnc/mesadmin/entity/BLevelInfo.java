package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：批次等级信息Model
 * 创建人：刘福志
 * 创建时间：2017-5-26
 * 修改人：
 * 修改时间：
 */
public class BLevelInfo {
    private int ruid;
    private String guid;
    private String bLName;
    private String creator;
    private Date createTime;
    private String lastModifyMan;
    private Date lastModifyTime;
    private String remark;

    public String getbLName() {
        return bLName;
    }

    public void setbLName(String bLName) {
        this.bLName = bLName;
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