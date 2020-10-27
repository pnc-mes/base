package pnc.mesadmin.dto.SaveMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 16:13
 * @Description:
 */
public class SaveMaxTimeInfoReq02  implements Serializable {
    @JsonProperty("MaxTimeRd")
    private int MaxTimeRd;

    @JsonProperty("MaxTimeWindowName")
    private String MaxTimeWindowName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("StartSpecVerRd")
    private int StartSpecVerRd;

    @JsonProperty("EndSpecVerRd")
    private int EndSpecVerRd;

    @JsonProperty("MaxTime")
    private float MaxTime;

    @JsonProperty("PresetTime")
    private float PresetTime;

    @JsonProperty("PresetEmailMessageRd")
    private int PresetEmailMessageRd;

    @JsonProperty("PresetEmailDistributionRd")
    private int PresetEmailDistributionRd;

    @JsonProperty("OverTimeAction")
    private String OverTimeAction;

    @JsonProperty("OverEmailDistributionRd")
    private int OverEmailDistributionRd;

    @JsonProperty("OverEmailMessageRd")
    private int OverEmailMessageRd;

    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public String getMaxTimeWindowName() {
        return MaxTimeWindowName;
    }
    @JsonIgnore
    public void setMaxTimeWindowName(String maxTimeWindowName) {
        MaxTimeWindowName = maxTimeWindowName;
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
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public int getStartSpecVerRd() {
        return StartSpecVerRd;
    }
    @JsonIgnore
    public void setStartSpecVerRd(int startSpecVerRd) {
        StartSpecVerRd = startSpecVerRd;
    }
    @JsonIgnore
    public int getEndSpecVerRd() {
        return EndSpecVerRd;
    }
    @JsonIgnore
    public void setEndSpecVerRd(int endSpecVerRd) {
        EndSpecVerRd = endSpecVerRd;
    }


    @JsonIgnore
    public float getMaxTime() {
        return MaxTime;
    }
    @JsonIgnore
    public void setMaxTime(float maxTime) {
        MaxTime = maxTime;
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
    public int getPresetEmailMessageRd() {
        return PresetEmailMessageRd;
    }
    @JsonIgnore
    public void setPresetEmailMessageRd(int presetEmailMessageRd) {
        PresetEmailMessageRd = presetEmailMessageRd;
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
    @JsonIgnore
    public int getMaxTimeRd() {
        return MaxTimeRd;
    }
    @JsonIgnore
    public void setMaxTimeRd(int maxTimeRd) {
        MaxTimeRd = maxTimeRd;
    }
    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }
    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }
}
