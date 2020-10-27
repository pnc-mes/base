package pnc.mesadmin.entity;

import java.util.Date;

//检验文档信息表
public class CheckDocInfo {
    private Integer Ruid;
    private String Guid;
    private String CheckDocName;
    private String RelType;
    private String RelGd;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public String getCheckDocName() {
        return CheckDocName;
    }

    public void setCheckDocName(String checkDocName) {
        CheckDocName = checkDocName;
    }

    public String getRelType() {
        return RelType;
    }

    public void setRelType(String relType) {
        RelType = relType;
    }

    public String getRelGd() {
        return RelGd;
    }

    public void setRelGd(String relGd) {
        RelGd = relGd;
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
