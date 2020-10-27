package pnc.mesadmin.dto.GetAllRMaNInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/26.
 */
public class GetAllRMaNInfoResD {
    @JsonProperty("RMaNRd")
    private int RMaNRd;

    @JsonProperty("RMaNCode")
    private String RMaNCode;
    @JsonIgnore
    public int getRMaNRd() {
        return RMaNRd;
    }
    @JsonIgnore
    public void setRMaNRd(int RMaNRd) {
        this.RMaNRd = RMaNRd;
    }
    @JsonIgnore
    public String getRMaNCode() {
        return RMaNCode;
    }
    @JsonIgnore
    public void setRMaNCode(String RMaNCode) {
        this.RMaNCode = RMaNCode;
    }
}
