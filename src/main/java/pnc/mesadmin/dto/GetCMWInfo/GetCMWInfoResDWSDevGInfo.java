package pnc.mesadmin.dto.GetCMWInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/18.
 */
public class GetCMWInfoResDWSDevGInfo {

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("DevName")
    private String DevName;

    @JsonProperty("DevStatus")
    private String DevStatus;

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

    @JsonIgnore
    public String getDevStatus() {
        return DevStatus;
    }

    @JsonIgnore
    public void setDevStatus(String devStatus) {
        DevStatus = devStatus;
    }
}
