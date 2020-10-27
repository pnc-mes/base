package pnc.mesadmin.dto.SaveMaPerionInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: haozan
 * @Date: 2018/9/6 16:13
 * @Description:
 */
public class SaveMaPerionInfoReq00 implements Serializable {
    @JsonProperty("MaPeriodName")
    private String MaPeriodName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("PeriodTime")
    private Float PeriodTime;

    @JsonProperty("PresetTime")
    private Float PresetTime;

    @JsonProperty("PresetEmailDistributionRd")
    private Integer PresetEmailDistributionRd;

    @JsonProperty("PresetEmailMessageRd")
    private int PresetEmailMessageRd;

    @JsonProperty("OverTimeAction")
    private String OverTimeAction;

    @JsonProperty("OverEmailDistributionRd")
    private int OverEmailDistributionRd;

    @JsonProperty("OverEmailMessageRd")
    private int OverEmailMessageRd;

    @JsonProperty("Remark")
    private String Remark;


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
    public Float getPeriodTime() {
        return PeriodTime;
    }
    @JsonIgnore
    public void setPeriodTime(Float periodTime) {
        PeriodTime = periodTime;
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
    public Integer getPresetEmailDistributionRd() {
        return PresetEmailDistributionRd;
    }
    @JsonIgnore
    public void setPresetEmailDistributionRd(Integer presetEmailDistributionRd) {
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
    public String getOverTimeAction() {
        return OverTimeAction;
    }
    @JsonIgnore
    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
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
}
