package pnc.mesadmin.dto.GetMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pnc.mesadmin.dto.GetMaxTimeInfo.*;

/**
 * @Auther: haozan
 * @Date: 2018/9/6
 * @Description:
 */
public class GetMaPerionInfoResD {
    @JsonProperty("MaPerionRd")
    private int MaPerionRd;

    @JsonProperty("MaPeriodName")
    private String MaPeriodName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("PeriodTime")
    private float PeriodTime;

    @JsonProperty("PresetTime")
    private float PresetTime;

    @JsonProperty("PresetEmailDistributionInfo")
    private PresetEmailDistributionInfo presetEmailDistributionInfo;

    @JsonProperty("PresetEmailMessageInfo")
    private PresetEmailMessageInfo presetEmailMessageInfo;

    @JsonProperty("OverTimeAction")
    private String OverTimeAction;

    @JsonProperty("OverdueEmailDistributionInfo")
    private OverdueEmailDistributionInfo overdueEmailDistributionInfo;

    @JsonProperty("OverdueEmailMessageInfo")
    private OverdueEmailMessageInfo overdueEmailMessageInfo;

    @JsonProperty("Creator")
    private String Creator;

    @JsonProperty("CreateTime")
    private String CreateTime;

    @JsonProperty("LastModifyMan")
    private String LastModifyMan;

    @JsonProperty("LastModifyTime")
    private String LastModifyTime;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getMaPerionRd() {
        return MaPerionRd;
    }
    @JsonIgnore
    public void setMaPerionRd(int maPerionRd) {
        MaPerionRd = maPerionRd;
    }
    @JsonIgnore
    public String getMaPeriodName() {
        return MaPeriodName;
    }
    @JsonIgnore
    public void setMaPeriodName(String maPeriodName) {
        MaPeriodName = maPeriodName;
    }
    @JsonIgnore
    public String getDescription() {
        return Description;
    }
    @JsonIgnore
    public void setDescription(String description) {
        Description = description;
    }
    @JsonIgnore
    public float getPeriodTime() {
        return PeriodTime;
    }
    @JsonIgnore
    public void setPeriodTime(float periodTime) {
        PeriodTime = periodTime;
    }
    @JsonIgnore
    public float getPresetTime() {
        return PresetTime;
    }
    @JsonIgnore
    public void setPresetTime(float presetTime) {
        PresetTime = presetTime;
    }
    @JsonIgnore
    public PresetEmailDistributionInfo getPresetEmailDistributionInfo() {
        return presetEmailDistributionInfo;
    }
    @JsonIgnore
    public void setPresetEmailDistributionInfo(PresetEmailDistributionInfo presetEmailDistributionInfo) {
        this.presetEmailDistributionInfo = presetEmailDistributionInfo;
    }
    @JsonIgnore
    public PresetEmailMessageInfo getPresetEmailMessageInfo() {
        return presetEmailMessageInfo;
    }
    @JsonIgnore
    public void setPresetEmailMessageInfo(PresetEmailMessageInfo presetEmailMessageInfo) {
        this.presetEmailMessageInfo = presetEmailMessageInfo;
    }
    @JsonIgnore
    public String getOverTimeAction() {
        return OverTimeAction;
    }
    @JsonIgnore
    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
    }
    @JsonIgnore
    public OverdueEmailDistributionInfo getOverdueEmailDistributionInfo() {
        return overdueEmailDistributionInfo;
    }
    @JsonIgnore
    public void setOverdueEmailDistributionInfo(OverdueEmailDistributionInfo overdueEmailDistributionInfo) {
        this.overdueEmailDistributionInfo = overdueEmailDistributionInfo;
    }
    @JsonIgnore
    public OverdueEmailMessageInfo getOverdueEmailMessageInfo() {
        return overdueEmailMessageInfo;
    }
    @JsonIgnore
    public void setOverdueEmailMessageInfo(OverdueEmailMessageInfo overdueEmailMessageInfo) {
        this.overdueEmailMessageInfo = overdueEmailMessageInfo;
    }
    @JsonIgnore
    public String getCreator() {
        return Creator;
    }
    @JsonIgnore
    public void setCreator(String creator) {
        Creator = creator;
    }
    @JsonIgnore
    public String getCreateTime() {
        return CreateTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
    @JsonIgnore
    public String getLastModifyMan() {
        return LastModifyMan;
    }
    @JsonIgnore
    public void setLastModifyMan(String lastModifyMan) {
        LastModifyMan = lastModifyMan;
    }
    @JsonIgnore
    public String getLastModifyTime() {
        return LastModifyTime;
    }
    @JsonIgnore
    public void setLastModifyTime(String lastModifyTime) {
        LastModifyTime = lastModifyTime;
    }
    @JsonIgnore
    public String getRemark() {
        return Remark;
    }
    @JsonIgnore
    public void setRemark(String remark) {
        Remark = remark;
    }
}
