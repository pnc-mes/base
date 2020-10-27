package pnc.mesadmin.entity;

import java.util.Date;

/**
 * @program: mesadmin
 * @description: 查询设置条件下拉框设置信息表
 * @author: yin.yang
 * @create: 2018-12-21
 **/
public class CSSearchDDInfo {
    private Integer Ruid;
    private String Guid;
    private String CSSearchFieldGd;
    private String DisFieldName;
    private String ValFiledName;
    private String SqlConfig;
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

    public String getCSSearchFieldGd() {
        return CSSearchFieldGd;
    }

    public void setCSSearchFieldGd(String CSSearchFieldGd) {
        this.CSSearchFieldGd = CSSearchFieldGd;
    }

    public String getDisFieldName() {
        return DisFieldName;
    }

    public void setDisFieldName(String disFieldName) {
        DisFieldName = disFieldName;
    }

    public String getValFiledName() {
        return ValFiledName;
    }

    public void setValFiledName(String valFiledName) {
        ValFiledName = valFiledName;
    }

    public String getSqlConfig() {
        return SqlConfig;
    }

    public void setSqlConfig(String sqlConfig) {
        SqlConfig = sqlConfig;
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
