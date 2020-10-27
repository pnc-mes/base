package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xfxi on 2017/6/9.
 */
public class SaveBomInfoReqBD04 implements Serializable{

    @JsonProperty("BomRd")
    private int BomRd;

    @JsonProperty("BomCode")
    private String BomCode;

    @JsonProperty("BomName")
    private String BomName;

    @JsonProperty("Version")
    private String Version;

    @JsonProperty("IsDef")
    private String IsDef;

    @JsonProperty("BMInfo")
    private List<SaveBomInfoReqBD04BMInfo> BMInfo;

    @JsonProperty("Remark")
    private String Remark;

    @JsonIgnore
    public int getBomRd() {
        return BomRd;
    }

    @JsonIgnore
    public void setBomRd(int bomRd) {
        BomRd = bomRd;
    }

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
    public String getIsDef() {
        return IsDef;
    }

    @JsonIgnore
    public void setIsDef(String isDef) {
        IsDef = isDef;
    }

    @JsonIgnore
    public List<SaveBomInfoReqBD04BMInfo> getBMInfo() {
        return BMInfo;
    }

    @JsonIgnore
    public void setBMInfo(List<SaveBomInfoReqBD04BMInfo> BMInfo) {
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
