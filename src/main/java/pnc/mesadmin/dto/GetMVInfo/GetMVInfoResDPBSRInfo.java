package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/7/29.
 */
public class GetMVInfoResDPBSRInfo implements Serializable{

    @JsonProperty("SRRd")
    private int SRRd;

    @JsonProperty("SRName")
    private String SRName;

    @JsonIgnore
    public int getSRRd() {
        return SRRd;
    }

    @JsonIgnore
    public void setSRRd(int SRRd) {
        this.SRRd = SRRd;
    }

    @JsonIgnore
    public String getSRName() {
        return SRName;
    }

    @JsonIgnore
    public void setSRName(String SRName) {
        this.SRName = SRName;
    }
}
