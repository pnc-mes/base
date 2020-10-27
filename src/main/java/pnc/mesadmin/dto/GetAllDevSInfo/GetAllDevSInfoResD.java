package pnc.mesadmin.dto.GetAllDevSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/18.
 */
public class GetAllDevSInfoResD implements Serializable {
    @JsonProperty("DevSRd")
    private int DevSRd;

    @JsonProperty("DevSName")
    private String DevSName;
    @JsonIgnore
    public int getDevSRd() {
        return DevSRd;
    }
    @JsonIgnore
    public void setDevSRd(int devSRd) {
        DevSRd = devSRd;
    }
    @JsonIgnore
    public String getDevSName() {
        return DevSName;
    }
    @JsonIgnore
    public void setDevSName(String devSName) {
        DevSName = devSName;
    }
}
