package pnc.mesadmin.dto.GetFrePlanInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: haozan
 * @Date: 2018/9/7
 * @Description:
 */
public class GetFrePlanInfoResD {
    @JsonProperty("FrePlanRd")
    private Integer FrePlanRd;

    @JsonProperty("FrePlanName")
    private String FrePlanName;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("ReaInfo")
    private ReaInfo ReaInfo;

    @JsonProperty("FileInfo")
    private FileInfo FileInfo;

    @JsonProperty("UseNum")
    private Integer UseNum;

    @JsonProperty("UnitType")
    private String UnitType;

    @JsonProperty("Reference")
    private String Reference;

    @JsonProperty("UpUseNum")
    private Integer UpUseNum;

    @JsonProperty("DownUseNum")
    private Integer DownUseNum;

    @JsonProperty("PresetUseNum")
    private Integer PresetUseNum;

    @JsonProperty("PresetEmailDistributionInfo")
    private PresetEmailDistributionInfo PresetEmailDistributionInfo;

    @JsonProperty("PresetEmailMessageInfo")
    private PresetEmailMessageInfo PresetEmailMessageInfo;

    @JsonProperty("StartEmailDistributionInfo")
    private StartEmailDistributionInfo StartEmailDistributionInfo;

    @JsonProperty("StartEmailMessageInfo")
    private StartEmailMessageInfo StartEmailMessageInfo;

    /*@JsonProperty("OverTimeAction")
    private String OverTimeAction;
*/
    @JsonProperty("OverdueEmailDistributionInfo")
    private OverdueEmailDistributionInfo OverdueEmailDistributionInfo;

    @JsonProperty("OverdueEmailMessageInfo")
    private OverdueEmailMessageInfo OverdueEmailMessageInfo;

    @JsonProperty("FrePlanDtlInfo")
    private List<FrePlanDtlInfo> FrePlanDtlInfo;

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
    public Integer getFrePlanRd() {
        return FrePlanRd;
    }
    @JsonIgnore
    public void setFrePlanRd(Integer frePlanRd) {
        FrePlanRd = frePlanRd;
    }
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
    public pnc.mesadmin.dto.GetFrePlanInfo.ReaInfo getReaInfo() {
        return ReaInfo;
    }
    @JsonIgnore
    public void setReaInfo(pnc.mesadmin.dto.GetFrePlanInfo.ReaInfo reaInfo) {
        ReaInfo = reaInfo;
    }
    @JsonIgnore
    public pnc.mesadmin.dto.GetFrePlanInfo.FileInfo getFileInfo() {
        return FileInfo;
    }
    @JsonIgnore
    public void setFileInfo(pnc.mesadmin.dto.GetFrePlanInfo.FileInfo fileInfo) {
        FileInfo = fileInfo;
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

    public pnc.mesadmin.dto.GetFrePlanInfo.PresetEmailDistributionInfo getPresetEmailDistributionInfo() {
        return PresetEmailDistributionInfo;
    }

    @JsonIgnore
    public void setPresetEmailDistributionInfo(pnc.mesadmin.dto.GetFrePlanInfo.PresetEmailDistributionInfo presetEmailDistributionInfo) {
        PresetEmailDistributionInfo = presetEmailDistributionInfo;
    }

    @JsonIgnore
    public pnc.mesadmin.dto.GetFrePlanInfo.PresetEmailMessageInfo getPresetEmailMessageInfo() {
        return PresetEmailMessageInfo;
    }

    @JsonIgnore
    public void setPresetEmailMessageInfo(pnc.mesadmin.dto.GetFrePlanInfo.PresetEmailMessageInfo presetEmailMessageInfo) {
        PresetEmailMessageInfo = presetEmailMessageInfo;
    }

    @JsonIgnore
    public pnc.mesadmin.dto.GetFrePlanInfo.StartEmailDistributionInfo getStartEmailDistributionInfo() {
        return StartEmailDistributionInfo;
    }

    @JsonIgnore
    public void setStartEmailDistributionInfo(pnc.mesadmin.dto.GetFrePlanInfo.StartEmailDistributionInfo startEmailDistributionInfo) {
        StartEmailDistributionInfo = startEmailDistributionInfo;
    }

    @JsonIgnore
    public pnc.mesadmin.dto.GetFrePlanInfo.StartEmailMessageInfo getStartEmailMessageInfo() {
        return StartEmailMessageInfo;
    }

    @JsonIgnore
    public void setStartEmailMessageInfo(pnc.mesadmin.dto.GetFrePlanInfo.StartEmailMessageInfo startEmailMessageInfo) {
        StartEmailMessageInfo = startEmailMessageInfo;
    }

   /* @JsonIgnore
    public String getOverTimeAction() {
        return OverTimeAction;
    }

    @JsonIgnore
    public void setOverTimeAction(String overTimeAction) {
        OverTimeAction = overTimeAction;
    }*/

    @JsonIgnore
    public pnc.mesadmin.dto.GetFrePlanInfo.OverdueEmailDistributionInfo getOverdueEmailDistributionInfo() {
        return OverdueEmailDistributionInfo;
    }

    @JsonIgnore
    public void setOverdueEmailDistributionInfo(pnc.mesadmin.dto.GetFrePlanInfo.OverdueEmailDistributionInfo overdueEmailDistributionInfo) {
        OverdueEmailDistributionInfo = overdueEmailDistributionInfo;
    }

    @JsonIgnore
    public pnc.mesadmin.dto.GetFrePlanInfo.OverdueEmailMessageInfo getOverdueEmailMessageInfo() {
        return OverdueEmailMessageInfo;
    }

    @JsonIgnore
    public void setOverdueEmailMessageInfo(pnc.mesadmin.dto.GetFrePlanInfo.OverdueEmailMessageInfo overdueEmailMessageInfo) {
        OverdueEmailMessageInfo = overdueEmailMessageInfo;
    }

    @JsonIgnore
    public List<pnc.mesadmin.dto.GetFrePlanInfo.FrePlanDtlInfo> getFrePlanDtlInfo() {
        return FrePlanDtlInfo;
    }

    @JsonIgnore
    public void setFrePlanDtlInfo(List<pnc.mesadmin.dto.GetFrePlanInfo.FrePlanDtlInfo> frePlanDtlInfo) {
        FrePlanDtlInfo = frePlanDtlInfo;
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
    public String getReference() {
        return Reference;
    }
    @JsonIgnore
    public void setReference(String reference) {
        Reference = reference;
    }
}
