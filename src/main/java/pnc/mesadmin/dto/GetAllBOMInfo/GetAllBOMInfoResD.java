package pnc.mesadmin.dto.GetAllBOMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetAllBOMInfoResD implements Serializable{

    @JsonProperty("BomRd")
    private int BomRd;

    @JsonProperty("BomCode")
    private String BomCode;

    @JsonProperty("BomName")
    private String BomName;

    @JsonProperty("BomVerRd")
    private int BomVerRd;

    @JsonProperty("Version")
    private String Version;

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
    public int getBomVerRd() {
        return BomVerRd;
    }

    @JsonIgnore
    public void setBomVerRd(int bomVerRd) {
        BomVerRd = bomVerRd;
    }

    @JsonIgnore
    public String getVersion() {
        return Version;
    }

    @JsonIgnore
    public void setVersion(String version) {
        Version = version;
    }
}
