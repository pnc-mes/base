package pnc.mesadmin.dto.GetMVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVInfoResDSPInfo implements Serializable{

    @JsonProperty("SPRd")
    private int SPRd;

    @JsonProperty("SPName")
    private String SPName;

    @JsonIgnore
    public int getSPRd() {
        return SPRd;
    }

    @JsonIgnore
    public void setSPRd(int SPRd) {
        this.SPRd = SPRd;
    }

    @JsonIgnore
    public String getSPName() {
        return SPName;
    }

    @JsonIgnore
    public void setSPName(String SPName) {
        this.SPName = SPName;
    }
}
