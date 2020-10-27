package pnc.mesadmin.dto.GetCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:24
 * @Description:
 */
public class GetCyclePlanInfoResD implements Serializable {
    @JsonProperty("CyclePlanRd")
    private int CyclePlanRd;
    @JsonProperty("CyclePlanName")
    private String CyclePlanName;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("StarDate")
    private String StarDate;
    @JsonProperty("EndDate")
    private String EndDate;

    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("MTType")
    private String MTType;
    @JsonProperty("UpTime")
    private float UpTime;
    @JsonProperty("DownTime")
    private float DownTime;
    @JsonProperty("PresetTime")
    private float PresetTime;
    /*@JsonProperty("OverTimeAction")
    private String OverTimeAction;*/
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
    @JsonProperty("ReaInfo")
    private GetCyclePlanInfoResDRea ReaInfo;
    @JsonProperty("FileInfo")
    private GetCyclePlanInfoResDFile FileInfo;
    @JsonProperty("DayInfo")
    private GetCyclePlanInfoResDDay DayInfo;
    @JsonProperty("WeekInfo")
    private GetCyclePlanInfoResDWeek WeekInfo;
    @JsonProperty("MonthInfo")
    private GetCyclePlanInfoResDMonth MonthInfo;
    @JsonProperty("YearInfo")
    private GetCyclePlanInfoResDYear YearInfo;
    @JsonProperty("DesInfo")
    private GetCyclePlanInfoResDDes DesInfo;
    @JsonProperty("QuarterInfo")
    private GetCyclePlanInfoResDQuarter QuarterInfo;
    @JsonProperty("PresetEmailDistributionInfo")
    private GetCyclePlanInfoResDPresetEmailDistribution PresetEmailDistributionInfo;
    @JsonProperty("PresetEmailMessageInfo")
    private GetCyclePlanInfoResDPresetEmailMessage PresetEmailMessageInfo;
    @JsonProperty("StartEmailDistributionInfo")
    private GetCyclePlanInfoResDStartEmailDistribution StartEmailDistributionInfo;
    @JsonProperty("StartEmailMessageInfo")
    private GetCyclePlanInfoResDStartEmailMessage StartEmailMessageInfo;
    @JsonProperty("OverdueEmailDistributionInfo")
    private GetCyclePlanInfoResDOverdueEmailDistribution OverdueEmailDistributionInfo;
    @JsonProperty("OverdueEmailMessageInfo")
    private GetCyclePlanInfoResDOverdueEmailMessage OverdueEmailMessageInfo;
    @JsonProperty("CyclePlanDtlInfo")
    private List<GetCyclePlanInfoResDCyclePlanDtl> CyclePlanDtlInfo;
    @JsonIgnore
    public int getCyclePlanRd() {
        return CyclePlanRd;
    }
    @JsonIgnore
    public void setCyclePlanRd(int cyclePlanRd) {
        CyclePlanRd = cyclePlanRd;
    }

    @JsonIgnore
    public String getCyclePlanName() {
        return CyclePlanName;
    }
    @JsonIgnore
    public void setCyclePlanName(String cyclePlanName) {
        CyclePlanName = cyclePlanName;
    }
    @JsonIgnore
    public String getStatus() {
        return Status;
    }
    @JsonIgnore
    public void setStatus(String status) {
        Status = status;
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
    public String getStarDate() {
        return StarDate;
    }
    @JsonIgnore
    public void setStarDate(String starDate) {
        StarDate = starDate;
    }
    @JsonIgnore
    public String getEndDate() {
        return EndDate;
    }
    @JsonIgnore
    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
    @JsonIgnore
    public String getMTType() {
        return MTType;
    }
    @JsonIgnore
    public void setMTType(String MTType) {
        this.MTType = MTType;
    }
    @JsonIgnore
    public float getUpTime() {
        return UpTime;
    }
    @JsonIgnore
    public void setUpTime(float upTime) {
        UpTime = upTime;
    }
    @JsonIgnore
    public float getDownTime() {
        return DownTime;
    }
    @JsonIgnore
    public void setDownTime(float downTime) {
        DownTime = downTime;
    }
    @JsonIgnore
    public float getPresetTime() {
        return PresetTime;
    }
    @JsonIgnore
    public void setPresetTime(float presetTime) {
        PresetTime = presetTime;
    }
    /*@JsonIgnore
    public String getOverTimeAction() {
        return OverTimeAction;
    }
    @JsonIgnore
    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
    }*/
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
    @JsonIgnore
    public GetCyclePlanInfoResDRea getReaInfo() {
        return ReaInfo;
    }
    @JsonIgnore
    public void setReaInfo(GetCyclePlanInfoResDRea reaInfo) {
        ReaInfo = reaInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDFile getFileInfo() {
        return FileInfo;
    }
    @JsonIgnore
    public void setFileInfo(GetCyclePlanInfoResDFile fileInfo) {
        FileInfo = fileInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDDay getDayInfo() {
        return DayInfo;
    }
    @JsonIgnore
    public void setDayInfo(GetCyclePlanInfoResDDay dayInfo) {
        DayInfo = dayInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDWeek getWeekInfo() {
        return WeekInfo;
    }
    @JsonIgnore
    public void setWeekInfo(GetCyclePlanInfoResDWeek weekInfo) {
        WeekInfo = weekInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDMonth getMonthInfo() {
        return MonthInfo;
    }
    @JsonIgnore
    public void setMonthInfo(GetCyclePlanInfoResDMonth monthInfo) {
        MonthInfo = monthInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDYear getYearInfo() {
        return YearInfo;
    }
    @JsonIgnore
    public void setYearInfo(GetCyclePlanInfoResDYear yearInfo) {
        YearInfo = yearInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDDes getDesInfo() {
        return DesInfo;
    }
    @JsonIgnore
    public void setDesInfo(GetCyclePlanInfoResDDes desInfo) {
        DesInfo = desInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDQuarter getQuarterInfo() {
        return QuarterInfo;
    }
    @JsonIgnore
    public void setQuarterInfo(GetCyclePlanInfoResDQuarter quarterInfo) {
        QuarterInfo = quarterInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDPresetEmailDistribution getPresetEmailDistributionInfo() {
        return PresetEmailDistributionInfo;
    }
    @JsonIgnore
    public void setPresetEmailDistributionInfo(GetCyclePlanInfoResDPresetEmailDistribution presetEmailDistributionInfo) {
        PresetEmailDistributionInfo = presetEmailDistributionInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDPresetEmailMessage getPresetEmailMessageInfo() {
        return PresetEmailMessageInfo;
    }
    @JsonIgnore
    public void setPresetEmailMessageInfo(GetCyclePlanInfoResDPresetEmailMessage presetEmailMessageInfo) {
        PresetEmailMessageInfo = presetEmailMessageInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDStartEmailDistribution getStartEmailDistributionInfo() {
        return StartEmailDistributionInfo;
    }
    @JsonIgnore
    public void setStartEmailDistributionInfo(GetCyclePlanInfoResDStartEmailDistribution startEmailDistributionInfo) {
        StartEmailDistributionInfo = startEmailDistributionInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDStartEmailMessage getStartEmailMessageInfo() {
        return StartEmailMessageInfo;
    }
    @JsonIgnore
    public void setStartEmailMessageInfo(GetCyclePlanInfoResDStartEmailMessage startEmailMessageInfo) {
        StartEmailMessageInfo = startEmailMessageInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDOverdueEmailDistribution getOverdueEmailDistributionInfo() {
        return OverdueEmailDistributionInfo;
    }
    @JsonIgnore
    public void setOverdueEmailDistributionInfo(GetCyclePlanInfoResDOverdueEmailDistribution overdueEmailDistributionInfo) {
        OverdueEmailDistributionInfo = overdueEmailDistributionInfo;
    }
    @JsonIgnore
    public GetCyclePlanInfoResDOverdueEmailMessage getOverdueEmailMessageInfo() {
        return OverdueEmailMessageInfo;
    }
    @JsonIgnore
    public void setOverdueEmailMessageInfo(GetCyclePlanInfoResDOverdueEmailMessage overdueEmailMessageInfo) {
        OverdueEmailMessageInfo = overdueEmailMessageInfo;
    }
    @JsonIgnore
    public List<GetCyclePlanInfoResDCyclePlanDtl> getCyclePlanDtlInfo() {
        return CyclePlanDtlInfo;
    }
    @JsonIgnore
    public void setCyclePlanDtlInfo(List<GetCyclePlanInfoResDCyclePlanDtl> cyclePlanDtlInfo) {
        CyclePlanDtlInfo = cyclePlanDtlInfo;
    }
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
}
