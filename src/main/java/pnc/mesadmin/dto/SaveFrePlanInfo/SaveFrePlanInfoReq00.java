package pnc.mesadmin.dto.SaveFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class SaveFrePlanInfoReq00 implements Serializable {
    @JsonProperty("FrePlanName")
    private String FrePlanName;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("ReaRd")
    private Integer ReaRd;

    @JsonProperty("FileVerRd")
    private Integer FileVerRd;

    @JsonProperty("UseNum")
    private Integer UseNum;

    @JsonProperty("UnitType")
    private String UnitType;

    @JsonProperty("UpUseNum")
    private Integer UpUseNum;
    @JsonProperty("Reference")
    private String Reference;
    @JsonProperty("DownUseNum")
    private Integer DownUseNum;

    @JsonProperty("PresetUseNum")
    private Integer PresetUseNum;

    @JsonProperty("PresetEmailMessageRd")
    private Integer PresetEmailMessageRd;

    @JsonProperty("PresetEmailDistributionRd")
    private Integer PresetEmailDistributionRd;

    @JsonProperty("StartEmailDistributionRd")
    private Integer StartEmailDistributionRd;

    @JsonProperty("StartEmailMessageRd")
    private Integer StartEmailMessageRd;

   /* @JsonProperty("OverTimeAction")
    private String OverTimeAction;*/

    @JsonProperty("OverEmailDistributionRd")
    private Integer OverEmailDistributionRd;

    @JsonProperty("OverEmailMessageRd")
    private Integer OverEmailMessageRd;

    @JsonProperty("FrePlanDtlInfo")
    private List<FrePlanDtlInfo> FrePlanDtlInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getFrePlanName() {
        return FrePlanName;
    }
    @JsonIgnore
    public void setFrePlanName(String frePlanName) {
        FrePlanName = frePlanName;
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
    public Integer getReaRd() {
        return ReaRd;
    }
    @JsonIgnore
    public void setReaRd(Integer reaRd) {
        ReaRd = reaRd;
    }
    @JsonIgnore
    public Integer getFileVerRd() {
        return FileVerRd;
    }
    @JsonIgnore
    public void setFileVerRd(Integer fileVerRd) {
        FileVerRd = fileVerRd;
    }
    @JsonIgnore
    public Integer getUseNum() {
        return UseNum;
    }
    @JsonIgnore
    public void setUseNum(Integer useNum) {
        UseNum = useNum;
    }
    @JsonIgnore
    public String getUnitType() {
        return UnitType;
    }
    @JsonIgnore
    public void setUnitType(String unitType) {
        UnitType = unitType;
    }
    @JsonIgnore
    public Integer getUpUseNum() {
        return UpUseNum;
    }
    @JsonIgnore
    public void setUpUseNum(Integer upUseNum) {
        UpUseNum = upUseNum;
    }
    @JsonIgnore
    public Integer getDownUseNum() {
        return DownUseNum;
    }
    @JsonIgnore
    public void setDownUseNum(Integer downUseNum) {
        DownUseNum = downUseNum;
    }
    @JsonIgnore
    public Integer getPresetUseNum() {
        return PresetUseNum;
    }
    @JsonIgnore
    public void setPresetUseNum(Integer presetUseNum) {
        PresetUseNum = presetUseNum;
    }
    @JsonIgnore
    public Integer getPresetEmailMessageRd() {
        return PresetEmailMessageRd;
    }
    @JsonIgnore
    public void setPresetEmailMessageRd(Integer presetEmailMessageRd) {
        PresetEmailMessageRd = presetEmailMessageRd;
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
    public Integer getStartEmailDistributionRd() {
        return StartEmailDistributionRd;
    }
    @JsonIgnore
    public void setStartEmailDistributionRd(Integer startEmailDistributionRd) {
        StartEmailDistributionRd = startEmailDistributionRd;
    }
    @JsonIgnore
    public Integer getStartEmailMessageRd() {
        return StartEmailMessageRd;
    }
    @JsonIgnore
    public void setStartEmailMessageRd(Integer startEmailMessageRd) {
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
    public Integer getOverEmailDistributionRd() {
        return OverEmailDistributionRd;
    }
    @JsonIgnore
    public void setOverEmailDistributionRd(Integer overEmailDistributionRd) {
        OverEmailDistributionRd = overEmailDistributionRd;
    }
    @JsonIgnore
    public Integer getOverEmailMessageRd() {
        return OverEmailMessageRd;
    }
    @JsonIgnore
    public void setOverEmailMessageRd(Integer overEmailMessageRd) {
        OverEmailMessageRd = overEmailMessageRd;
    }

    @JsonIgnore
    public List<pnc.mesadmin.dto.SaveFrePlanInfo.FrePlanDtlInfo> getFrePlanDtlInfo() {
        return FrePlanDtlInfo;
    }

    @JsonIgnore
    public void setFrePlanDtlInfo(List<pnc.mesadmin.dto.SaveFrePlanInfo.FrePlanDtlInfo> frePlanDtlInfo) {
        FrePlanDtlInfo = frePlanDtlInfo;
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
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
}
