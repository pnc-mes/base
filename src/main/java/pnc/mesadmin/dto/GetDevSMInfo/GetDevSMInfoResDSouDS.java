package pnc.mesadmin.dto.GetDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class GetDevSMInfoResDSouDS implements Serializable {
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
