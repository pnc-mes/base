package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by haozan on 2018/9/7.d
 * 次数保养计划触发规则信息表实体类
 */
public class FrePlanTiggerRulelnfo {
    private Integer Ruid;
    private String Guid;
    private String FrePlanGd;
    private Integer UpUseNum;
    private Integer DownUseNum;
    private Integer PresetUseNum;
    private String PresetEmailDistributionGd;
    private String PresetEmailMessageGd;
    private String StartEmailDistributionGd;
    private String StartEmailMessageGd;
    //private String OverTimeAction;
    private String OverdueEmailDistributionGd;
    private String OverdueEmailMessageGd;
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

    public String getFrePlanGd() {
        return FrePlanGd;
    }

    public void setFrePlanGd(String frePlanGd) {
        FrePlanGd = frePlanGd;
    }

    public Integer getUpUseNum() {
        return UpUseNum;
    }

    public void setUpUseNum(Integer upUseNum) {
        UpUseNum = upUseNum;
    }

    public Integer getDownUseNum() {
        return DownUseNum;
    }

    public void setDownUseNum(Integer downUseNum) {
        DownUseNum = downUseNum;
    }

    public Integer getPresetUseNum() {
        return PresetUseNum;
    }

    public void setPresetUseNum(Integer presetUseNum) {
        PresetUseNum = presetUseNum;
    }

    public String getPresetEmailDistributionGd() {
        return PresetEmailDistributionGd;
    }

    public void setPresetEmailDistributionGd(String presetEmailDistributionGd) {
        PresetEmailDistributionGd = presetEmailDistributionGd;
    }

    public String getPresetEmailMessageGd() {
        return PresetEmailMessageGd;
    }

    public void setPresetEmailMessageGd(String presetEmailMessageGd) {
        PresetEmailMessageGd = presetEmailMessageGd;
    }

    public String getStartEmailDistributionGd() {
        return StartEmailDistributionGd;
    }

    public void setStartEmailDistributionGd(String startEmailDistributionGd) {
        StartEmailDistributionGd = startEmailDistributionGd;
    }

    public String getStartEmailMessageGd() {
        return StartEmailMessageGd;
    }

    public void setStartEmailMessageGd(String startEmailMessageGd) {
        StartEmailMessageGd = startEmailMessageGd;
    }

    /*public String getOverTimeAction() {
        return OverTimeAction;
    }

    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
    }*/

    public String getOverdueEmailDistributionGd() {
        return OverdueEmailDistributionGd;
    }

    public void setOverdueEmailDistributionGd(String overdueEmailDistributionGd) {
        OverdueEmailDistributionGd = overdueEmailDistributionGd;
    }

    public String getOverdueEmailMessageGd() {
        return OverdueEmailMessageGd;
    }

    public void setOverdueEmailMessageGd(String overdueEmailMessageGd) {
        OverdueEmailMessageGd = overdueEmailMessageGd;
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
