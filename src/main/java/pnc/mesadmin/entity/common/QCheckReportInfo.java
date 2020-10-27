package pnc.mesadmin.entity.common;

import java.util.Date;

//检验报告信息表
public class QCheckReportInfo {
    private Integer Ruid;
    private String Guid;
    private String QCheckReportCode;
    private String QCheckMaCode;
    private String QCheckMaType;
    private Integer CheckNum;
    private String SChecker;
    private Date SCheckTime;
    private String MaVerGd;
    private String SourceName;
    private String FinalResult;
    private String CheckDocGd;
    private String Status;
    private String Checker; //质检员
    private String CheckRemark;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Reviewer;   //核准人

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

    public String getQCheckReportCode() {
        return QCheckReportCode;
    }

    public void setQCheckReportCode(String QCheckReportCode) {
        this.QCheckReportCode = QCheckReportCode;
    }

    public String getQCheckMaCode() {
        return QCheckMaCode;
    }

    public void setQCheckMaCode(String QCheckMaCode) {
        this.QCheckMaCode = QCheckMaCode;
    }

    public String getMaVerGd() {
        return MaVerGd;
    }

    public void setMaVerGd(String maVerGd) {
        MaVerGd = maVerGd;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getFinalResult() {
        return FinalResult;
    }

    public void setFinalResult(String finalResult) {
        FinalResult = finalResult;
    }

    public String getCheckDocGd() {
        return CheckDocGd;
    }

    public void setCheckDocGd(String checkDocGd) {
        CheckDocGd = checkDocGd;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getChecker() {
        return Checker;
    }

    public void setChecker(String checker) {
        Checker = checker;
    }

    public String getCheckRemark() {
        return CheckRemark;
    }

    public void setCheckRemark(String checkRemark) {
        CheckRemark = checkRemark;
    }

    public String getQCheckMaType() {
        return QCheckMaType;
    }

    public void setQCheckMaType(String QCheckMaType) {
        this.QCheckMaType = QCheckMaType;
    }

    public Integer getCheckNum() {
        return CheckNum;
    }

    public void setCheckNum(Integer checkNum) {
        CheckNum = checkNum;
    }

    public String getSChecker() {
        return SChecker;
    }

    public void setSChecker(String SChecker) {
        this.SChecker = SChecker;
    }

    public Date getSCheckTime() {
        return SCheckTime;
    }

    public void setSCheckTime(Date SCheckTime) {
        this.SCheckTime = SCheckTime;
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

    public String getReviewer() {
        return Reviewer;
    }

    public void setReviewer(String reviewer) {
        Reviewer = reviewer;
    }
}
