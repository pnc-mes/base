package pnc.mesadmin.dto.GetMOQInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class GetMOQInfoReqBD01 {

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("MOType")
    private String MOType;

    @JsonProperty("SpecRd")
    private int SpecRd;

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
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
}
