package pnc.mesadmin.dto.GetDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class GetDevSInfoReq00 implements Serializable {
    @JsonProperty("DevSRd")
    private int DevSRd;
    @JsonIgnore
    public int getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(int devSRd) {
        DevSRd = devSRd;
    }
}
