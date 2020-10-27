package pnc.mesadmin.dto.GetAllRCGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by test on 2017/8/11.
 */
public class GetAllRCGInfoResD {
    @JsonProperty("RCGRd")
    private int RCGRd;

    @JsonProperty("RCGpName")
    private String RCGpName;

    @JsonIgnore
    public int getRCGRd() {
        return RCGRd;
    }
    @JsonIgnore
    public void setRCGRd(int RCGRd) {
        this.RCGRd = RCGRd;
    }
    @JsonIgnore
    public String getRCGpName() {
        return RCGpName;
    }
    @JsonIgnore
    public void setRCGpName(String RCGpName) {
        this.RCGpName = RCGpName;
    }
}
