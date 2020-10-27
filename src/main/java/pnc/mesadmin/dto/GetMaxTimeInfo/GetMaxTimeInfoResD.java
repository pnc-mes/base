package pnc.mesadmin.dto.GetMaxTimeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @Auther: zhangliangliang
 * @Date: 2018/7/30 11:30
 * @Description:
 */
public class GetMaxTimeInfoResD {
    @JsonProperty("MaxTimeRd")
    private int MaxTimeRd;

    @JsonProperty("MaxTimeWindowName")
    private String MaxTimeWindowName;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("MaxTime")
    private float MaxTime;

    @JsonProperty("PresetTime")
    private float PresetTime;

    @JsonProperty("OverTimeAction")
    private String OverTimeAction;

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

    @JsonProperty("WFInfo")
    private GetMaxTimeInfoResDWF WFInfo;
    @JsonProperty("MaInfo")
    private GetMaxTimeInfoResDMa MaInfo;
    @JsonProperty("StartSpecVerInfo")
    private GetMaxTimeInfoResDStartSpec StartSpecVerInfo;
    @JsonProperty("EndSpecVerInfo")
    private GetMaxTimeInfoResDEndSpec EndSpecVerInfo;
    @JsonProperty("PresetEmailDistributionInfo")
    private GetMaxTimeInfoResDPresetEmailDistribution PresetEmailDistributionInfo;
    @JsonProperty("PresetEmailMessageInfo")
    private GetMaxTimeInfoResDPresetEmailMessage PresetEmailMessageInfo;
    @JsonProperty("OverdueEmailDistributionInfo")
    private GetMaxTimeInfoResDOverdueEmailDistribution OverdueEmailDistributionInfo;
    @JsonProperty("OverdueEmailMessageInfo")
    private GetMaxTimeInfoResDOverdueEmailMessage OverdueEmailMessageInfo;


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
    public int getMaxTimeRd() {
        return MaxTimeRd;
    }
    @JsonIgnore
    public void setMaxTimeRd(int maxTimeRd) {
        MaxTimeRd = maxTimeRd;
    }
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
    public String getOverTimeAction() {
        return OverTimeAction;
    }
    @JsonIgnore
    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDMa getMaInfo() {
        return MaInfo;
    }
    @JsonIgnore
    public void setMaInfo(GetMaxTimeInfoResDMa maInfo) {
        MaInfo = maInfo;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDStartSpec getStartSpecVerInfo() {
        return StartSpecVerInfo;
    }
    @JsonIgnore
    public void setStartSpecVerInfo(GetMaxTimeInfoResDStartSpec startSpecVerInfo) {
        StartSpecVerInfo = startSpecVerInfo;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDEndSpec getEndSpecVerInfo() {
        return EndSpecVerInfo;
    }
    @JsonIgnore
    public void setEndSpecVerInfo(GetMaxTimeInfoResDEndSpec endSpecVerInfo) {
        EndSpecVerInfo = endSpecVerInfo;
    }


    @JsonIgnore
    public GetMaxTimeInfoResDPresetEmailDistribution getPresetEmailDistributionInfo() {
        return PresetEmailDistributionInfo;
    }
    @JsonIgnore
    public void setPresetEmailDistributionInfo(GetMaxTimeInfoResDPresetEmailDistribution presetEmailDistributionInfo) {
        PresetEmailDistributionInfo = presetEmailDistributionInfo;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDPresetEmailMessage getPresetEmailMessageInfo() {
        return PresetEmailMessageInfo;
    }
    @JsonIgnore
    public void setPresetEmailMessageInfo(GetMaxTimeInfoResDPresetEmailMessage presetEmailMessageInfo) {
        PresetEmailMessageInfo = presetEmailMessageInfo;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDOverdueEmailDistribution getOverdueEmailDistributionInfo() {
        return OverdueEmailDistributionInfo;
    }
    @JsonIgnore
    public void setOverdueEmailDistributionInfo(GetMaxTimeInfoResDOverdueEmailDistribution overdueEmailDistributionInfo) {
        OverdueEmailDistributionInfo = overdueEmailDistributionInfo;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDOverdueEmailMessage getOverdueEmailMessageInfo() {
        return OverdueEmailMessageInfo;
    }
    @JsonIgnore
    public void setOverdueEmailMessageInfo(GetMaxTimeInfoResDOverdueEmailMessage overdueEmailMessageInfo) {
        OverdueEmailMessageInfo = overdueEmailMessageInfo;
    }
    @JsonIgnore
    public GetMaxTimeInfoResDWF getWFInfo() {
        return WFInfo;
    }
    @JsonIgnore
    public void setWFInfo(GetMaxTimeInfoResDWF WFInfo) {
        this.WFInfo = WFInfo;
    }
}
