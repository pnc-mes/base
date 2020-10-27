package pnc.mesadmin.dto.GetMOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/16.
 */
public class GetMOInfoReqBD00 {

    @JsonProperty("WoCode")
    private String WoCode;

    @JsonProperty("MOType")
    private String MOType;

    @JsonProperty("MORd")
    private int MORd;

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
    public int getMORd() {
        return MORd;
    }

    @JsonIgnore
    public void setMORd(int MORd) {
        this.MORd = MORd;
    }
}
