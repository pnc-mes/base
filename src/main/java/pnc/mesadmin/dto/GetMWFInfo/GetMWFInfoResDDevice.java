package pnc.mesadmin.dto.GetMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/11/30.
 */
public class GetMWFInfoResDDevice implements Serializable {
    @JsonProperty("DevGpRd")
    private int DevGpRd;

    @JsonProperty("DevGpName")
    private String DevGpName;

    @JsonIgnore
    public int getDevGpRd() {
        return DevGpRd;
    }

    @JsonIgnore
    public void setDevGpRd(int devGpRd) {
        DevGpRd = devGpRd;
    }

    @JsonIgnore
    public String getDevGpName() {
        return DevGpName;
    }

    @JsonIgnore
    public void setDevGpName(String devGpName) {
        DevGpName = devGpName;
    }
}
