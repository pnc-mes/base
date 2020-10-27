package pnc.mesadmin.entity;

import java.util.Date;

public class CheckTRelDtlInfo {
    private Integer Ruid;
    private String Guid;
    private String CheckTRelGd;
    private String CheckTempGd;
    private String CheckLevelGd;
    private String AQLRuleGd;
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

    public String getCheckTRelGd() {
        return CheckTRelGd;
    }

    public void setCheckTRelGd(String checkTRelGd) {
        CheckTRelGd = checkTRelGd;
    }

    public String getCheckTempGd() {
        return CheckTempGd;
    }

    public void setCheckTempGd(String checkTempGd) {
        CheckTempGd = checkTempGd;
    }

    public String getCheckLevelGd() {
        return CheckLevelGd;
    }

    public void setCheckLevelGd(String checkLevelGd) {
        CheckLevelGd = checkLevelGd;
    }

    public String getAQLRuleGd() {
        return AQLRuleGd;
    }

    public void setAQLRuleGd(String AQLRuleGd) {
        this.AQLRuleGd = AQLRuleGd;
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
