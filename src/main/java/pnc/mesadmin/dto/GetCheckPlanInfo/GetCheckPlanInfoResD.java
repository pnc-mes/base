package pnc.mesadmin.dto.GetCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/7 10:24
 * @Description:
 */
public class GetCheckPlanInfoResD implements Serializable {
    @JsonProperty("CheckPlanRd")
    private int CheckPlanRd;
    @JsonProperty("CheckPlanName")
    private String CheckPlanName;
    @JsonProperty("CheckPlanType")
    private String CheckPlanType;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Reference")
    private String Reference;
    @JsonProperty("Place")
    private String Place;
    @JsonProperty("StarDate")
    private String StarDate;
    @JsonProperty("EndDate")
    private String EndDate;
    @JsonProperty("MTType")
    private String MTType;
    @JsonProperty("TimeType")
    private String TimeType;
    @JsonProperty("UpTime")
    private float UpTime;
    @JsonProperty("DownTime")
    private float DownTime;
    @JsonProperty("PresetTime")
    private float PresetTime;
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
    @JsonProperty("DayInfo")
    private GetCheckPlanInfoResDDay DayInfo;
    @JsonProperty("WeekInfo")
    private GetCheckPlanInfoResDWeek WeekInfo;
    @JsonProperty("MonthInfo")
    private GetCheckPlanInfoResDMonth MonthInfo;
    @JsonProperty("YearInfo")
    private GetCheckPlanInfoResDYear YearInfo;
    @JsonProperty("DesInfo")
    private GetCheckPlanInfoResDDes DesInfo;
    @JsonProperty("QuarterInfo")
    private GetCheckPlanInfoResDQuarter QuarterInfo;
    @JsonProperty("FileInfo")
    private GetCheckPlanInfoResFileInfo FileInfo;
    @JsonProperty("PresetEmailDistributionInfo")
    private GetCheckPlanInfoResDPresetEmailDistribution PresetEmailDistributionInfo;
    @JsonProperty("PresetEmailMessageInfo")
    private GetCheckPlanInfoResDPresetEmailMessage PresetEmailMessageInfo;
    @JsonProperty("StartEmailDistributionInfo")
    private GetCheckPlanInfoResDStartEmailDistribution StartEmailDistributionInfo;
    @JsonProperty("StartEmailMessageInfo")
    private GetCheckPlanInfoResDStartEmailMessage StartEmailMessageInfo;
    @JsonProperty("OverdueEmailDistributionInfo")
    private GetCheckPlanInfoResDOverdueEmailDistribution OverdueEmailDistributionInfo;
    @JsonProperty("OverdueEmailMessageInfo")
    private GetCheckPlanInfoResDOverdueEmailMessage OverdueEmailMessageInfo;
    @JsonProperty("CheckPlanDtlInfo")
    private List<GetCheckPlanInfoDCheckPlanDtl> CheckPlanDtlInfo;
    @JsonIgnore
    public int getCheckPlanRd() {
        return CheckPlanRd;
    }
    @JsonIgnore
    public void setCheckPlanRd(int checkPlanRd) {
        CheckPlanRd = checkPlanRd;
    }
    @JsonIgnore
    public String getCheckPlanName() {
        return CheckPlanName;
    }
    @JsonIgnore
    public void setCheckPlanName(String checkPlanName) {
        CheckPlanName = checkPlanName;
    }
    @JsonIgnore
    public String getCheckPlanType() {
        return CheckPlanType;
    }
    @JsonIgnore
    public void setCheckPlanType(String checkPlanType) {
        CheckPlanType = checkPlanType;
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
    public GetCheckPlanInfoResDDay getDayInfo() {
        return DayInfo;
    }
    @JsonIgnore
    public void setDayInfo(GetCheckPlanInfoResDDay dayInfo) {
        DayInfo = dayInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDWeek getWeekInfo() {
        return WeekInfo;
    }
    @JsonIgnore
    public void setWeekInfo(GetCheckPlanInfoResDWeek weekInfo) {
        WeekInfo = weekInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDMonth getMonthInfo() {
        return MonthInfo;
    }
    @JsonIgnore
    public void setMonthInfo(GetCheckPlanInfoResDMonth monthInfo) {
        MonthInfo = monthInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDYear getYearInfo() {
        return YearInfo;
    }
    @JsonIgnore
    public void setYearInfo(GetCheckPlanInfoResDYear yearInfo) {
        YearInfo = yearInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDDes getDesInfo() {
        return DesInfo;
    }
    @JsonIgnore
    public void setDesInfo(GetCheckPlanInfoResDDes desInfo) {
        DesInfo = desInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDQuarter getQuarterInfo() {
        return QuarterInfo;
    }
    @JsonIgnore
    public void setQuarterInfo(GetCheckPlanInfoResDQuarter quarterInfo) {
        QuarterInfo = quarterInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDPresetEmailDistribution getPresetEmailDistributionInfo() {
        return PresetEmailDistributionInfo;
    }
    @JsonIgnore
    public void setPresetEmailDistributionInfo(GetCheckPlanInfoResDPresetEmailDistribution presetEmailDistributionInfo) {
        PresetEmailDistributionInfo = presetEmailDistributionInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDPresetEmailMessage getPresetEmailMessageInfo() {
        return PresetEmailMessageInfo;
    }
    @JsonIgnore
    public void setPresetEmailMessageInfo(GetCheckPlanInfoResDPresetEmailMessage presetEmailMessageInfo) {
        PresetEmailMessageInfo = presetEmailMessageInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDStartEmailDistribution getStartEmailDistributionInfo() {
        return StartEmailDistributionInfo;
    }
    @JsonIgnore
    public void setStartEmailDistributionInfo(GetCheckPlanInfoResDStartEmailDistribution startEmailDistributionInfo) {
        StartEmailDistributionInfo = startEmailDistributionInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDStartEmailMessage getStartEmailMessageInfo() {
        return StartEmailMessageInfo;
    }
    @JsonIgnore
    public void setStartEmailMessageInfo(GetCheckPlanInfoResDStartEmailMessage startEmailMessageInfo) {
        StartEmailMessageInfo = startEmailMessageInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDOverdueEmailDistribution getOverdueEmailDistributionInfo() {
        return OverdueEmailDistributionInfo;
    }
    @JsonIgnore
    public void setOverdueEmailDistributionInfo(GetCheckPlanInfoResDOverdueEmailDistribution overdueEmailDistributionInfo) {
        OverdueEmailDistributionInfo = overdueEmailDistributionInfo;
    }
    @JsonIgnore
    public GetCheckPlanInfoResDOverdueEmailMessage getOverdueEmailMessageInfo() {
        return OverdueEmailMessageInfo;
    }
    @JsonIgnore
    public void setOverdueEmailMessageInfo(GetCheckPlanInfoResDOverdueEmailMessage overdueEmailMessageInfo) {
        OverdueEmailMessageInfo = overdueEmailMessageInfo;
    }
    @JsonIgnore
    public List<GetCheckPlanInfoDCheckPlanDtl> getCheckPlanDtlInfo() {
        return CheckPlanDtlInfo;
    }
    @JsonIgnore
    public void setCheckPlanDtlInfo(List<GetCheckPlanInfoDCheckPlanDtl> checkPlanDtlInfo) {
        CheckPlanDtlInfo = checkPlanDtlInfo;
    }
    @JsonIgnore
    public String getTimeType() {
        return TimeType;
    }
    @JsonIgnore
    public void setTimeType(String timeType) {
        TimeType = timeType;
    }
    @JsonIgnore
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
    @JsonIgnore
    public String getPlace() {
        return Place;
    }
    @JsonIgnore
    public void setPlace(String place) {
        Place = place;
    }
    @JsonIgnore
    public GetCheckPlanInfoResFileInfo getFileInfo() {
        return FileInfo;
    }
    @JsonIgnore
    public void setFileInfo(GetCheckPlanInfoResFileInfo fileInfo) {
        FileInfo = fileInfo;
    }
}
