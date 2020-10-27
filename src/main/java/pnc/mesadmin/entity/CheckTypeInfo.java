package pnc.mesadmin.entity;

import java.util.Date;

//检验类型信息表
public class CheckTypeInfo {
    private Integer Ruid;
    private String Guid;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String CheckTypeCode;
    private String CheckTypeName;

    public String getCheckTypeCode() {
        return CheckTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        CheckTypeCode = checkTypeCode;
    }

    public String getCheckTypeName() {
        return CheckTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        CheckTypeName = checkTypeName;
    }

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
