package pnc.mesadmin.dto.GetNRMTotalInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by zhaochao on 2017/10/25.
 */
public class GetNRMTotalInfoResDBLInfo implements Serializable {
    @JsonProperty("LRd")
    private int LRd;
    @JsonProperty("LName")
    private String LName;

    @JsonIgnore
    public int getLRd() {
        return LRd;
    }
    @JsonIgnore
    public void setLRd(int LRd) {
        this.LRd = LRd;
    }
    @JsonIgnore
    public String getLName() {
        return LName;
    }
    @JsonIgnore
    public void setLName(String LName) {
        this.LName = LName;
    }
}
