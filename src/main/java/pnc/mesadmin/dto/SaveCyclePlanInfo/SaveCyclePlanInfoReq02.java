package pnc.mesadmin.dto.SaveCyclePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCyclePlanInfoReq02 implements Serializable {
    @JsonProperty("CyclePlanRd")
    private int CyclePlanRd;

    @JsonProperty("CyclePlanName")
    private String CyclePlanName;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Reference")
    private String Reference;
    @JsonProperty("ReaRd")
    private int ReaRd;

    @JsonProperty("FileVerRd")
    private int FileVerRd;

    @JsonProperty("EndDate")
    private String EndDate;

    @JsonProperty("StarDate")
    private String StarDate;

    @JsonProperty("MTType")
    private String MTType;

    @JsonProperty("UpTime")
    private Float UpTime;

    @JsonProperty("DownTime")
    private Float DownTime;

    @JsonProperty("PresetTime")
    private Float PresetTime;

    @JsonProperty("PresetEmailDistributionRd")
    private int PresetEmailDistributionRd;

    @JsonProperty("PresetEmailMessageRd")
    private int PresetEmailMessageRd;

    @JsonProperty("StartEmailDistributionRd")
    private int StartEmailDistributionRd;

    @JsonProperty("StartEmailMessageRd")
    private int StartEmailMessageRd;

    /*@JsonProperty("OverTimeAction")
    private String OverTimeAction;*/

    @JsonProperty("OverEmailDistributionRd")
    private int OverEmailDistributionRd;

    @JsonProperty("OverEmailMessageRd")
    private int OverEmailMessageRd;

    @JsonProperty("Remark")
    private String Remark;

    @JsonProperty("CyclePlanDtlInfo")
    private List<SaveCyclePlanInfoReq02CyclePlanDtl>  CyclePlanDtlInfo;

    @JsonProperty("QuarterInfo")
    private SaveCyclePlanInfoReq02QuarterInfo QuarterInfo;

    @JsonProperty("DesInfo")
    private SaveCyclePlanInfoReq02DesInfo DesInfo;

    @JsonProperty("YearInfo")
    private SaveCyclePlanInfoReq02YearInfo YearInfo;

    @JsonProperty("MonthInfo")
    private SaveCyclePlanInfoReq02MonthInfo MonthInfo;

    @JsonProperty("WeekInfo")
    private SaveCyclePlanInfoReq02WeekInfo WeekInfo;

    @JsonProperty("DayInfo")
    private SaveCyclePlanInfoReq02DayInfo DayInfo;
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
    public int getReaRd() {
        return ReaRd;
    }
    @JsonIgnore
    public void setReaRd(int reaRd) {
        ReaRd = reaRd;
    }
    @JsonIgnore
    public int getFileVerRd() {
        return FileVerRd;
    }
    @JsonIgnore
    public void setFileVerRd(int fileVerRd) {
        FileVerRd = fileVerRd;
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
    public String getStarDate() {
        return StarDate;
    }
    @JsonIgnore
    public void setStarDate(String starDate) {
        StarDate = starDate;
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
    public Float getUpTime() {
        return UpTime;
    }
    @JsonIgnore
    public void setUpTime(Float upTime) {
        UpTime = upTime;
    }
    @JsonIgnore
    public Float getDownTime() {
        return DownTime;
    }
    @JsonIgnore
    public void setDownTime(Float downTime) {
        DownTime = downTime;
    }
    @JsonIgnore
    public Float getPresetTime() {
        return PresetTime;
    }
    @JsonIgnore
    public void setPresetTime(Float presetTime) {
        PresetTime = presetTime;
    }
    @JsonIgnore
    public int getPresetEmailDistributionRd() {
        return PresetEmailDistributionRd;
    }
    @JsonIgnore
    public void setPresetEmailDistributionRd(int presetEmailDistributionRd) {
        PresetEmailDistributionRd = presetEmailDistributionRd;
    }
    @JsonIgnore
    public int getPresetEmailMessageRd() {
        return PresetEmailMessageRd;
    }
    @JsonIgnore
    public void setPresetEmailMessageRd(int presetEmailMessageRd) {
        PresetEmailMessageRd = presetEmailMessageRd;
    }
    @JsonIgnore
    public int getStartEmailDistributionRd() {
        return StartEmailDistributionRd;
    }
    @JsonIgnore
    public void setStartEmailDistributionRd(int startEmailDistributionRd) {
        StartEmailDistributionRd = startEmailDistributionRd;
    }
    @JsonIgnore
    public int getStartEmailMessageRd() {
        return StartEmailMessageRd;
    }
    @JsonIgnore
    public void setStartEmailMessageRd(int startEmailMessageRd) {
        StartEmailMessageRd = startEmailMessageRd;
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
    public int getOverEmailDistributionRd() {
        return OverEmailDistributionRd;
    }
    @JsonIgnore
    public void setOverEmailDistributionRd(int overEmailDistributionRd) {
        OverEmailDistributionRd = overEmailDistributionRd;
    }
    @JsonIgnore
    public int getOverEmailMessageRd() {
        return OverEmailMessageRd;
    }
    @JsonIgnore
    public void setOverEmailMessageRd(int overEmailMessageRd) {
        OverEmailMessageRd = overEmailMessageRd;
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
    public List<SaveCyclePlanInfoReq02CyclePlanDtl> getCyclePlanDtlInfo() {
        return CyclePlanDtlInfo;
    }
    @JsonIgnore
    public void setCyclePlanDtlInfo(List<SaveCyclePlanInfoReq02CyclePlanDtl> cyclePlanDtlInfo) {
        CyclePlanDtlInfo = cyclePlanDtlInfo;
    }
    @JsonIgnore
    public SaveCyclePlanInfoReq02QuarterInfo getQuarterInfo() {
        return QuarterInfo;
    }
    @JsonIgnore
    public void setQuarterInfo(SaveCyclePlanInfoReq02QuarterInfo quarterInfo) {
        QuarterInfo = quarterInfo;
    }
    @JsonIgnore
    public SaveCyclePlanInfoReq02DesInfo getDesInfo() {
        return DesInfo;
    }
    @JsonIgnore
    public void setDesInfo(SaveCyclePlanInfoReq02DesInfo desInfo) {
        DesInfo = desInfo;
    }
    @JsonIgnore
    public SaveCyclePlanInfoReq02YearInfo getYearInfo() {
        return YearInfo;
    }
    @JsonIgnore
    public void setYearInfo(SaveCyclePlanInfoReq02YearInfo yearInfo) {
        YearInfo = yearInfo;
    }
    @JsonIgnore
    public SaveCyclePlanInfoReq02MonthInfo getMonthInfo() {
        return MonthInfo;
    }
    @JsonIgnore
    public void setMonthInfo(SaveCyclePlanInfoReq02MonthInfo monthInfo) {
        MonthInfo = monthInfo;
    }
    @JsonIgnore
    public SaveCyclePlanInfoReq02WeekInfo getWeekInfo() {
        return WeekInfo;
    }
    @JsonIgnore
    public void setWeekInfo(SaveCyclePlanInfoReq02WeekInfo weekInfo) {
        WeekInfo = weekInfo;
    }
    @JsonIgnore
    public SaveCyclePlanInfoReq02DayInfo getDayInfo() {
        return DayInfo;
    }
    @JsonIgnore
    public void setDayInfo(SaveCyclePlanInfoReq02DayInfo dayInfo) {
        DayInfo = dayInfo;
    }
    @JsonIgnore
    public int getCyclePlanRd() {
        return CyclePlanRd;
    }
    @JsonIgnore
    public void setCyclePlanRd(int cyclePlanRd) {
        CyclePlanRd = cyclePlanRd;
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
