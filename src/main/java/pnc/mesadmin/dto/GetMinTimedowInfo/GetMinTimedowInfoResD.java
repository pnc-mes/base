package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoResD implements Serializable {

    @JsonProperty("MinTimeWindoRd")
    private int MinTimeWindoRd;
    @JsonProperty("MinTimeWindowName")
    private String MinTimeWindowName;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("MaInfo")
    private GetMinTimedowInfoResDMaterialInfo MaInfo;
    @JsonProperty("SpecInfo")
    private GetMinTimedowInfoResDSpecGdInfo SpecInfo;
    @JsonProperty("DevInfo")
    private GetMinTimedowInfoResDDevgpInfo DevInfo;
    @JsonProperty("MinTime")
    private float MinTime;
    @JsonProperty("OverTimeAction")
    private String OverTimeAction;
    @JsonProperty("EmailDistributionInfo")
    private GetMinTimedowInfoResDEmailDistributionInfo EmailDistributionInfo;
    @JsonProperty("EmailMessageInfo")
    private GetMinTimedowInfoResDEmailMessageInfo EmailMessageInfo;
    @JsonProperty("WFInfo")
    private GetMinTimedowInfoResDWFInfo WFInfo;

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
    public int getMinTimeWindoRd() {
        return MinTimeWindoRd;
    }
    @JsonIgnore
    public void setMinTimeWindoRd(int minTimeWindoRd) {
        MinTimeWindoRd = minTimeWindoRd;
    }
    @JsonIgnore
    public String getMinTimeWindowName() {
        return MinTimeWindowName;
    }
    @JsonIgnore
    public void setMinTimeWindowName(String minTimeWindowName) {
        MinTimeWindowName = minTimeWindowName;
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
    public GetMinTimedowInfoResDMaterialInfo getMaInfo() {
        return MaInfo;
    }
    @JsonIgnore
    public void setMaInfo(GetMinTimedowInfoResDMaterialInfo maInfo) {
        MaInfo = maInfo;
    }
    @JsonIgnore
    public GetMinTimedowInfoResDSpecGdInfo getSpecInfo() {
        return SpecInfo;
    }
    @JsonIgnore
    public void setSpecInfo(GetMinTimedowInfoResDSpecGdInfo specInfo) {
        SpecInfo = specInfo;
    }
    @JsonIgnore
    public GetMinTimedowInfoResDDevgpInfo getDevInfo() {
        return DevInfo;
    }
    @JsonIgnore
    public void setDevInfo(GetMinTimedowInfoResDDevgpInfo devInfo) {
        DevInfo = devInfo;
    }
    @JsonIgnore
    public float getMinTime() {
        return MinTime;
    }

    @JsonIgnore
    public void setMinTime(float minTime) {
        MinTime = minTime;
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
    public GetMinTimedowInfoResDWFInfo getWFInfo() {
        return WFInfo;
    }
    @JsonIgnore
    public void setWFInfo(GetMinTimedowInfoResDWFInfo WFInfo) {
        this.WFInfo = WFInfo;
    }

    @JsonIgnore
    public GetMinTimedowInfoResDEmailDistributionInfo getEmailDistributionInfo() {
        return EmailDistributionInfo;
    }
    @JsonIgnore
    public void setEmailDistributionInfo(GetMinTimedowInfoResDEmailDistributionInfo emailDistributionInfo) {
        EmailDistributionInfo = emailDistributionInfo;
    }
    @JsonIgnore
    public GetMinTimedowInfoResDEmailMessageInfo getEmailMessageInfo() {
        return EmailMessageInfo;
    }
    @JsonIgnore
    public void setEmailMessageInfo(GetMinTimedowInfoResDEmailMessageInfo emailMessageInfo) {
        EmailMessageInfo = emailMessageInfo;
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
