package pnc.mesadmin.entity;

import java.util.Date;

//AQL规则明细信息表
public class AQLRuleDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String AQLRuleGd;
    private Float MinNum;
    private Float MaxNum;
    private Float SamplingNum;
    private Float AcceptNum;
    private Float RejectNum;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;


    public String getAQLRuleGd() {
        return AQLRuleGd;
    }

    public void setAQLRuleGd(String AQLRuleGd) {
        this.AQLRuleGd = AQLRuleGd;
    }

    public Float getMinNum() {
        return MinNum;
    }

    public void setMinNum(Float minNum) {
        MinNum = minNum;
    }

    public Float getMaxNum() {
        return MaxNum;
    }

    public void setMaxNum(Float maxNum) {
        MaxNum = maxNum;
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
