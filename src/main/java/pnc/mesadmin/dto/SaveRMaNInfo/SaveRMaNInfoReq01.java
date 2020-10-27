package pnc.mesadmin.dto.SaveRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by PNC on 2017/9/26.
 */
public class SaveRMaNInfoReq01 {
    @JsonProperty("RMaNRd")
    private int  RMaNRd;

    @JsonIgnore
    public int getRMaNRd() {
        return RMaNRd;
    }
    @JsonIgnore
    public void setRMaNRd(int RMaNRd) {
        this.RMaNRd = RMaNRd;
    }
}
