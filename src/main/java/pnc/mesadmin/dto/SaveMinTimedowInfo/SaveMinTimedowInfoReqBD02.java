package pnc.mesadmin.dto.SaveMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by PNC on 2018/7/30.
 */
public class SaveMinTimedowInfoReqBD02 implements Serializable {
    @JsonProperty("MinTimeWindowRd")
    private int MinTimeWindowRd;
    @JsonProperty("MinTimeWindowName")
    private String MinTimeWindowName;
    @JsonProperty("Description")
    private String Description;
    @JsonProperty("MaVerRd")
    private int MaVerRd;
    @JsonProperty("WfVerRd")
    private int  WfVerRd;
    @JsonProperty("SpecVerRd")
    private int  SpecVerRd;
    @JsonProperty("DevRd")
    private int  DevRd;
    @JsonProperty("MinTime")
    private float MinTime;
    @JsonProperty("OverTimeAction")
    private String OverTimeAction;
    @JsonProperty("EmailDistributionRd")
    private int EmailDistributionRd;
    @JsonProperty("EmailMessageRd")
    private int EmailMessageRd;
    @JsonProperty("Remark")
    private String Remark;
    @JsonIgnore
    public int getMinTimeWindowRd() {
        return MinTimeWindowRd;
    }
    @JsonIgnore
    public void setMinTimeWindowRd(int minTimeWindowRd) {
        MinTimeWindowRd = minTimeWindowRd;
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
    public int getMaVerRd() {
        return MaVerRd;
    }
    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }
    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }
    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }
    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
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
    public int getEmailDistributionRd() {
        return EmailDistributionRd;
    }
    @JsonIgnore
    public void setEmailDistributionRd(int emailDistributionRd) {
        EmailDistributionRd = emailDistributionRd;
    }
    @JsonIgnore
    public int getEmailMessageRd() {
        return EmailMessageRd;
    }
    @JsonIgnore
    public void setEmailMessageRd(int emailMessageRd) {
        EmailMessageRd = emailMessageRd;
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
    public int getWfVerRd() {
        return WfVerRd;
    }
    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }
}
