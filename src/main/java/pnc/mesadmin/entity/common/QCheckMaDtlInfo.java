package pnc.mesadmin.entity.common;

import java.util.Date;

//请检单明细
public class QCheckMaDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String QCheckMaGd;
    private String BarType;
    private String BarCode;
    private String QCheckReason;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;
    private String QCheckReportGd; //检验报告标识


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

    public String getQCheckMaGd() {
        return QCheckMaGd;
    }

    public void setQCheckMaGd(String QCheckMaGd) {
        this.QCheckMaGd = QCheckMaGd;
    }

    public String getBarType() {
        return BarType;
    }

    public void setBarType(String barType) {
        BarType = barType;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getQCheckReason() {
        return QCheckReason;
    }

    public void setQCheckReason(String QCheckReason) {
        this.QCheckReason = QCheckReason;
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

    public String getQCheckReportGd() {
        return QCheckReportGd;
    }

    public void setQCheckReportGd(String QCheckReportGd) {
        this.QCheckReportGd = QCheckReportGd;
    }
}
