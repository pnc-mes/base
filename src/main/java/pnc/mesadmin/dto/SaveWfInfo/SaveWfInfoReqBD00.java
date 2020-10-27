package pnc.mesadmin.dto.SaveWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/9.
 */
public class SaveWfInfoReqBD00 implements Serializable {

    @JsonProperty("WFType")
    private String WFType;

    @JsonProperty("WfName")
    private String WfName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("WFJson")
    private String WFJson;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("WfInfo")
    private List<SaveWfInfoReqBD00WfInfo> WfInfo;

    @JsonProperty("Remark")
    private String Remark;

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
    public List<SaveWfInfoReqBD00WfInfo> getWfInfo() {
        return WfInfo;
    }

    @JsonIgnore
    public void setWfInfo(List<SaveWfInfoReqBD00WfInfo> wfInfo) {
        WfInfo = wfInfo;
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
