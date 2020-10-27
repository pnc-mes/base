package pnc.mesadmin.entity;

import java.util.Date;

//检验 模板 明细 信息表
public class CheckTempDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String CheckTempGd;
    private String RelGd;
    private String RelType; //00#检验项 01#检验类型
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;


    public String getCheckTempGd() {
        return CheckTempGd;
    }

    public void setCheckTempGd(String checkTempGd) {
        CheckTempGd = checkTempGd;
    }

    public String getRelGd() {
        return RelGd;
    }

    public void setRelGd(String relGd) {
        RelGd = relGd;
    }

    public String getRelType() {
        return RelType;
    }

    public void setRelType(String relType) {
        RelType = relType;
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
