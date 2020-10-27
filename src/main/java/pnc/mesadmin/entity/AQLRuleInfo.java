package pnc.mesadmin.entity;

import java.util.Date;

//AQL规则信息表
public class AQLRuleInfo {
    private Integer Ruid;
    private String Guid;
    private String AQLRuleName;
    private String CheckLevelGd;
    private String Creator;
    private Date CreateTime;
    private String LastModifyMan;
    private Date LastModifyTime;
    private String Remark;

    public String getAQLRuleName() {
        return AQLRuleName;
    }

    public void setAQLRuleName(String AQLRuleName) {
        this.AQLRuleName = AQLRuleName;
    }

    public String getCheckLevelGd() {
        return CheckLevelGd;
    }

    public void setCheckLevelGd(String checkLevelGd) {
        CheckLevelGd = checkLevelGd;
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
