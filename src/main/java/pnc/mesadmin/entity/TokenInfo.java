package pnc.mesadmin.entity;

import java.util.Date;

/**
 * 公司名称：驭航信息技术(上海)有限公司
 * 系统名称：PNC-MES管理系统
 * 子系统名称：用户令牌信息表实体类
 * 创建人：张亮亮
 * 创建时间：2017-06-23
 * 修改人：
 * 修改时间：
 */
public class TokenInfo {
    private int ruid;
    private String guid;
    private String userGd;
    private String token;
    private Date sValidTime;
    private Date eValidTime;
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

    public String getUserGd() {
        return userGd;
    }

    public void setUserGd(String userGd) {
        this.userGd = userGd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getsValidTime() {
        return sValidTime;
    }

    public void setsValidTime(Date sValidTime) {
        this.sValidTime = sValidTime;
    }

    public Date geteValidTime() {
        return eValidTime;
    }

    public void seteValidTime(Date eValidTime) {
        this.eValidTime = eValidTime;
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
