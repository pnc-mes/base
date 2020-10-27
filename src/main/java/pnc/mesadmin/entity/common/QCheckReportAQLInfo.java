package pnc.mesadmin.entity.common;

import java.util.Date;

//检验告报告AQL规则信息表
public class QCheckReportAQLInfo {
    private Integer Ruid;
    private String Guid;
    private String QCheckReportGd;
    private String CheckLevelName;
    private String AQLRuleName;
    private Float SamplingNum;
    private Float AcceptNum;
    private Float RejectNum;
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

    public String getCheckLevelName() {
        return CheckLevelName;
    }

    public void setCheckLevelName(String checkLevelName) {
        CheckLevelName = checkLevelName;
    }

    public String getAQLRuleName() {
        return AQLRuleName;
    }

    public void setAQLRuleName(String AQLRuleName) {
        this.AQLRuleName = AQLRuleName;
    }

    public Float getSamplingNum() {
        return SamplingNum;
    }

    public void setSamplingNum(Float samplingNum) {
        SamplingNum = samplingNum;
    }

    public Float getAcceptNum() {
        return AcceptNum;
    }

    public void setAcceptNum(Float acceptNum) {
        AcceptNum = acceptNum;
    }

    public Float getRejectNum() {
        return RejectNum;
    }

    public void setRejectNum(Float rejectNum) {
        RejectNum = rejectNum;
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
