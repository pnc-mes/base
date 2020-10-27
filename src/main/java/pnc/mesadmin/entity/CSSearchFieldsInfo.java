package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 查询设置条件字段 信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public class CSSearchFieldsInfo {
    private Integer Ruid;
    private String Guid;
    private String CSSearchGd;
    private String CdName;
    private String AliasName;
    //00#下拉框,01#文本框,02#日期段,03#时间段,04#多行文本
    private String CdType;
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

    public String getCSSearchGd() {
        return CSSearchGd;
    }

    public void setCSSearchGd(String CSSearchGd) {
        this.CSSearchGd = CSSearchGd;
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

    public String getCdType() {
        return CdType;
    }

    public void setCdType(String cdType) {
        CdType = cdType;
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
