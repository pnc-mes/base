package pnc.mesadmin.entity;

import java.util.Date;

//检验类型明细信息表
public class CheckTypeDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String CheckTypeGd;
    private String CheckItemName;
    private String CheckItemC;
    private String SureType;

    public String getCheckTypeGd() {
        return CheckTypeGd;
    }

    public void setCheckTypeGd(String checkTypeGd) {
        CheckTypeGd = checkTypeGd;
    }

    public String getCheckItemName() {
        return CheckItemName;
    }

    public void setCheckItemName(String checkItemName) {
        CheckItemName = checkItemName;
    }

    public String getCheckItemC() {
        return CheckItemC;
    }

    public void setCheckItemC(String checkItemC) {
        CheckItemC = checkItemC;
    }

    public String getSureType() {
        return SureType;
    }

    public void setSureType(String sureType) {
        SureType = sureType;
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
