package pnc.mesadmin.dto.GetMTInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/8/21.
 */
public class GetMTInfoReqBD00 implements Serializable {
    @JsonProperty("MTRd")
    private int MTRd;

    @JsonIgnore
    public int getMTRd() {
        return MTRd;
    }

    @JsonIgnore
    public void setMTRd(int MTRd) {
        this.MTRd = MTRd;
    }
}
