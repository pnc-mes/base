package pnc.mesadmin.dto.GetRCGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetRCGInfoReqBD00 implements Serializable{

    @JsonProperty("RCGRd")
    private int RCGRd;
    @JsonIgnore
    public int getRCGRd() {
        return RCGRd;
    }
    @JsonIgnore
    public void setRCGRd(int RCGRd) {
        this.RCGRd = RCGRd;
    }
}
