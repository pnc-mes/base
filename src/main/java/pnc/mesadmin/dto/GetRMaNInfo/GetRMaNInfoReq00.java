package pnc.mesadmin.dto.GetRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetRMaNInfoReq00 {
    @JsonProperty("RMaNRd")
    private int RMaNRd;
    @JsonIgnore
    public int getRMaNRd() {
        return RMaNRd;
    }
    @JsonIgnore
    public void setRMaNRd(int RMaNRd) {
        this.RMaNRd = RMaNRd;
    }
}

