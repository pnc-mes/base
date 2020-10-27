package pnc.mesadmin.entity;

import java.util.Date;

/**
 * Created by haozan on 2018/9/6.
 * 在线物料有效期信息表实体类
 */
public class OnLineMaPeriodInfo {

    private Integer Ruid;
    private String Guid;
    private String MaPeriodName;
    private String Description;
    private Float PeriodTime;
    private Float PresetTime;
    private String PresetEmailDistributionGd;
    private String PresetEmailMessageGd;
    private String OverTimeAction;
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

    public String getMaPeriodName() {
        return MaPeriodName;
    }

    public void setMaPeriodName(String maPeriodName) {
        MaPeriodName = maPeriodName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Float getPeriodTime() {
        return PeriodTime;
    }

    public void setPeriodTime(Float periodTime) {
        PeriodTime = periodTime;
    }

    public Float getPresetTime() {
        return PresetTime;
    }

    public void setPresetTime(Float presetTime) {
        PresetTime = presetTime;
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

    public String getOverTimeAction() {
        return OverTimeAction;
    }

    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
    }

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
