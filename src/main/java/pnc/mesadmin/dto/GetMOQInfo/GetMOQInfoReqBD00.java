package pnc.mesadmin.dto.GetMOQInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class GetMOQInfoReqBD00 {

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("MOType")
    private String MOType;

    @JsonProperty("SpecRd")
    private int SpecRd;
    @JsonProperty("MORd")
    private int MORd;

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonIgnore
    public String getWoCode() {
        return WoCode;
    }

    @JsonIgnore
    public void setWoCode(String woCode) {
        WoCode = woCode;
    }

    @JsonIgnore
    public String getMOType() {
        return MOType;
    }

    @JsonIgnore
    public void setMOType(String MOType) {
        this.MOType = MOType;
    }

    @JsonIgnore
    public int getSpecRd() {
        return SpecRd;
    }

    @JsonIgnore
    public void setSpecRd(int specRd) {
        SpecRd = specRd;
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
    public int getMORd() {
        return MORd;
    }

    @JsonIgnore
    public void setMORd(int MORd) {
        this.MORd = MORd;
    }
}
