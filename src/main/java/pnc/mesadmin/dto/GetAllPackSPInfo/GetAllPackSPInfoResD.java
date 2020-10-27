package pnc.mesadmin.dto.GetAllPackSPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/8/21.
 */
public class GetAllPackSPInfoResD implements Serializable{
    @JsonProperty("MPRd")
    private int MPRd;

    @JsonProperty("MPName")
    private String MPName;

    @JsonIgnore
    public int getMPRd() {
        return MPRd;
    }
    @JsonIgnore
    public void setMPRd(int MPRd) {
        this.MPRd = MPRd;
    }
    @JsonIgnore
    public String getMPName() {
        return MPName;
    }
    @JsonIgnore
    public void setMPName(String MPName) {
        this.MPName = MPName;
    }
}
