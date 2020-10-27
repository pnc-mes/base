package pnc.mesadmin.dto.GetWfVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetWfVInfoResD implements Serializable{

    @JsonProperty("WfRd")
    private int WfRd;

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("WfName")
    private String WfName;

    @JsonProperty("WFType")
    private String WFType;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("WFJson")
    private String WFJson;

    @JsonProperty("Status")
    private String Status;

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
    public int getWfRd() {
        return WfRd;
    }

    @JsonIgnore
    public void setWfRd(int wfRd) {
        WfRd = wfRd;
    }

    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }

    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }

    @JsonIgnore
    public String getWfName() {
        return WfName;
    }

    @JsonIgnore
    public void setWfName(String wfName) {
        WfName = wfName;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }

    @JsonIgnore
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }

    @JsonIgnore
    public String getWFJson() {
        return WFJson;
    }

    @JsonIgnore
    public void setWFJson(String WFJson) {
        this.WFJson = WFJson;
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
    public String getWFType() {
        return WFType;
    }
    @JsonIgnore
    public void setWFType(String WFType) {
        this.WFType = WFType;
    }
}
