package pnc.mesadmin.entity.common;

import java.util.Date;

//检验报告检验明细
public class QCheckReportItemInfo {
    private Integer Ruid;
    private String Guid;
    private String QCheckReportGd;
    private String QCheckReportAQLGd;
    private String CheckItemName;
    private String CheckItemC;
    private String CheckMethodName;
    private String SureType;
    private String SureResult;
    private Float RejNum;
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


    public String getQCheckReportGd() {
        return QCheckReportGd;
    }

    public void setQCheckReportGd(String QCheckReportGd) {
        this.QCheckReportGd = QCheckReportGd;
    }

    public String getQCheckReportAQLGd() {
        return QCheckReportAQLGd;
    }

    public void setQCheckReportAQLGd(String QCheckReportAQLGd) {
        this.QCheckReportAQLGd = QCheckReportAQLGd;
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

    public String getCheckMethodName() {
        return CheckMethodName;
    }

    public void setCheckMethodName(String checkMethodName) {
        CheckMethodName = checkMethodName;
    }

    public String getSureType() {
        return SureType;
    }

    public void setSureType(String sureType) {
        SureType = sureType;
    }

    public String getSureResult() {
        return SureResult;
    }

    public void setSureResult(String sureResult) {
        SureResult = sureResult;
    }

    public Float getRejNum() {
        return RejNum;
    }

    public void setRejNum(Float rejNum) {
        RejNum = rejNum;
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
