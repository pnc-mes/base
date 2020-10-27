package pnc.mesadmin.dto.SaveRCGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/8/14.
 */
public class SaveRCGpInfoReq00RCInfo implements Serializable{
    @JsonProperty("ReaRd")
    private int ReaRd;
    @JsonIgnore
    public int getReaRd() {
        return ReaRd;
    }
    @JsonIgnore
    public void setReaRd(int reaRd) {
        ReaRd = reaRd;
    }
}
