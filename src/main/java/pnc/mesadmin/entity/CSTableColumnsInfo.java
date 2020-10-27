package pnc.mesadmin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 表格设置输出字段配置信息表
 */

public class CSTableColumnsInfo implements Serializable {

    private Integer Ruid;
    private String Guid;
    private String CSTableGd;
    private String ColumnName;
    private String AliasName;
    private String ColumnType;
    private String IsDisplay;
    private Integer ColumnWidth;
    private String Truncated;
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

    public String getCSTableGd() {
        return CSTableGd;
    }

    public void setCSTableGd(String CSTableGd) {
        this.CSTableGd = CSTableGd;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public String getAliasName() {
        return AliasName;
    }

    public void setAliasName(String aliasName) {
        AliasName = aliasName;
    }

    public String getColumnType() {
        return ColumnType;
    }

    public void setColumnType(String columnType) {
        ColumnType = columnType;
    }

    public String getIsDisplay() {
        return IsDisplay;
    }

    public void setIsDisplay(String isDisplay) {
        IsDisplay = isDisplay;
    }

    public Integer getColumnWidth() {
        return ColumnWidth;
    }

    public void setColumnWidth(Integer columnWidth) {
        ColumnWidth = columnWidth;
    }

    public String getTruncated() {
        return Truncated;
    }

    public void setTruncated(String truncated) {
        Truncated = truncated;
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
