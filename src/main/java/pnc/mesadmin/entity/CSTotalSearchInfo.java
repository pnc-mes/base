package pnc.mesadmin.entity;
/*
统计设置查询条件配置信息
 */

import java.io.Serializable;
import java.util.Date;

public class CSTotalSearchInfo implements Serializable {

    private Integer Ruid;
    private String Guid;
    private String CSTotalGd;
    private String CdName;
    private String AliasName;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public Integer getRuid() {
        return Ruid;
    }

    public void setRuid(Integer ruid) {
        Ruid = ruid;
    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getCSTotalGd() {
        return CSTotalGd;
    }

    public void setCSTotalGd(String CSTotalGd) {
        this.CSTotalGd = CSTotalGd;
    }

    public String getCdName() {
        return CdName;
    }

    public void setCdName(String cdName) {
        CdName = cdName;
    }

    public String getAliasName() {
        return AliasName;
    }

    public void setAliasName(String aliasName) {
        AliasName = aliasName;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getLastModifyMan() {
        return LastModifyMan;
    }

    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }

    public Date getLastModifyTime() {
        return LastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
