package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoResDDevgpInfo implements Serializable {
    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("DevName")
    private String DevName;

    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }
    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
    }
    @JsonIgnore
    public String getDevName() {
        return DevName;
    }
    @JsonIgnore
    public void setDevName(String devName) {
        DevName = devName;
    }
}
