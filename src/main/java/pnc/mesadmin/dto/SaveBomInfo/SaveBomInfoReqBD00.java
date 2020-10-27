package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/8.
 */
public class SaveBomInfoReqBD00 implements Serializable{

    @JsonProperty("BomCode")
    private String BomCode;

    @JsonProperty("BomName")
    private String BomName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("VersionNo")
    private String VersionNo;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("BomNo")
    private String BomNo;

    @JsonProperty("BMInfo")
    private List<SaveBomInfoReqBD00BMInfo> BMInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public String getBomCode() {
        return BomCode;
    }

    @JsonIgnore
    public void setBomCode(String bomCode) {
        BomCode = bomCode;
    }

    @JsonIgnore
    public String getBomName() {
        return BomName;
    }

    @JsonIgnore
    public void setBomName(String bomName) {
        BomName = bomName;
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
    public String getVersionNo() {
        return VersionNo;
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
    public void setVersionNo(String versionNo) {
        VersionNo = versionNo;
    }

    @JsonIgnore
    public String getBomNo() {
        return BomNo;
    }

    @JsonIgnore
    public void setBomNo(String bomNo) {
        BomNo = bomNo;
    }

    @JsonIgnore
    public List<SaveBomInfoReqBD00BMInfo> getBMInfo() {
        return BMInfo;
    }

    @JsonIgnore
    public void setBMInfo(List<SaveBomInfoReqBD00BMInfo> BMInfo) {
        this.BMInfo = BMInfo;
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
