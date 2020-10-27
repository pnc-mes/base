package pnc.mesadmin.entity;

import java.util.Date;

//检验项信息表
public class CheckItemInfo {
    private Integer Ruid;
    private String Guid;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String  CheckMethodGd;//检验方法
    private String CheckItemCode; //检验项代码
    private String CheckItemName; //检验项名称
    private String CheckItemC; //检验项内容
    private String SureType; //确认方式

    public String getCheckItemCode() {
        return CheckItemCode;
    }

    public void setCheckItemCode(String checkItemCode) {
        CheckItemCode = checkItemCode;
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

    public String getCheckMethodGd() {
        return CheckMethodGd;
    }

    public void setCheckMethodGd(String checkMethodGd) {
        CheckMethodGd = checkMethodGd;
    }


}
