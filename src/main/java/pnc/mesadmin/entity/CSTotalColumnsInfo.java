package pnc.mesadmin.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 统计设置 输出字段配置信息
 */

public class CSTotalColumnsInfo implements Serializable {

    private Integer Ruid;
    private String Guid;
    private String CSTotalGd;
    private String ColumnName;
    private String OutPutText;
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

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public String getOutPutText() {
        return OutPutText;
    }

    public void setOutPutText(String outPutText) {
        OutPutText = outPutText;
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
