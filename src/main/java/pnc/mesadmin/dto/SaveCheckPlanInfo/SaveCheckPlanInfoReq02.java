package pnc.mesadmin.dto.SaveCheckPlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/9/6 15:16
 * @Description:
 */
public class SaveCheckPlanInfoReq02 implements Serializable {
    @JsonProperty("CheckPlanType")
    private String CheckPlanType;

    @JsonProperty("CheckPlanRd")
    private int CheckPlanRd;

    @JsonProperty("CheckPlanName")
    private String CheckPlanName;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Description")
    private String Description;
    @JsonProperty("Reference")
    private String Reference;

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

    @JsonProperty("OverEmailDistributionRd")
    private int OverEmailDistributionRd;

    @JsonProperty("OverEmailMessageRd")
    private int OverEmailMessageRd;

    @JsonProperty("Remark")
    private String Remark;
    @JsonProperty("FileVerRd")
    private int FileVerRd;

    @JsonProperty("CheckPlanDtlInfo")
    private List<SaveCheckPlanInfoReq02CyclePlanDtl>  CheckPlanDtlInfo;

    @JsonProperty("QuarterInfo")
    private SaveCheckPlanInfoReq02QuarterInfo QuarterInfo;

    @JsonProperty("DesInfo")
    private SaveCheckPlanInfoReq02DesInfo DesInfo;

    @JsonProperty("YearInfo")
    private SaveCheckPlanInfoReq02YearInfo YearInfo;

    @JsonProperty("MonthInfo")
    private SaveCheckPlanInfoReq02MonthInfo MonthInfo;

    @JsonProperty("WeekInfo")
    private SaveCheckPlanInfoReq02WeekInfo WeekInfo;

    @JsonProperty("DayInfo")
    private SaveCheckPlanInfoReq02DayInfo DayInfo;

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
    public SaveCheckPlanInfoReq02QuarterInfo getQuarterInfo() {
        return QuarterInfo;
    }
    @JsonIgnore
    public void setQuarterInfo(SaveCheckPlanInfoReq02QuarterInfo quarterInfo) {
        QuarterInfo = quarterInfo;
    }
    @JsonIgnore
    public SaveCheckPlanInfoReq02DesInfo getDesInfo() {
        return DesInfo;
    }
    @JsonIgnore
    public void setDesInfo(SaveCheckPlanInfoReq02DesInfo desInfo) {
        DesInfo = desInfo;
    }
    @JsonIgnore
    public SaveCheckPlanInfoReq02YearInfo getYearInfo() {
        return YearInfo;
    }
    @JsonIgnore
    public void setYearInfo(SaveCheckPlanInfoReq02YearInfo yearInfo) {
        YearInfo = yearInfo;
    }
    @JsonIgnore
    public SaveCheckPlanInfoReq02MonthInfo getMonthInfo() {
        return MonthInfo;
    }
    @JsonIgnore
    public void setMonthInfo(SaveCheckPlanInfoReq02MonthInfo monthInfo) {
        MonthInfo = monthInfo;
    }
    @JsonIgnore
    public SaveCheckPlanInfoReq02WeekInfo getWeekInfo() {
        return WeekInfo;
    }
    @JsonIgnore
    public void setWeekInfo(SaveCheckPlanInfoReq02WeekInfo weekInfo) {
        WeekInfo = weekInfo;
    }
    @JsonIgnore
    public SaveCheckPlanInfoReq02DayInfo getDayInfo() {
        return DayInfo;
    }
    @JsonIgnore
    public void setDayInfo(SaveCheckPlanInfoReq02DayInfo dayInfo) {
        DayInfo = dayInfo;
    }
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
    public List<SaveCheckPlanInfoReq02CyclePlanDtl> getCheckPlanDtlInfo() {
        return CheckPlanDtlInfo;
    }
    @JsonIgnore
    public void setCheckPlanDtlInfo(List<SaveCheckPlanInfoReq02CyclePlanDtl> checkPlanDtlInfo) {
        CheckPlanDtlInfo = checkPlanDtlInfo;
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
    public int getFileVerRd() {
        return FileVerRd;
    }
    @JsonIgnore
    public void setFileVerRd(int fileVerRd) {
        FileVerRd = fileVerRd;
    }
}
