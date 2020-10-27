package pnc.mesadmin.dto.SaveMODevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xfxi on 2017/8/29.
 */
public class SaveMODevInfoReqBD00 {

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonProperty("EoBaInfo")
    private List<SaveMODevInfoReqBD00EoBaInfo> EoBaInfo;

    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
    }

    @JsonIgnore
    public List<SaveMODevInfoReqBD00EoBaInfo> getEoBaInfo() {
        return EoBaInfo;
    }

    @JsonIgnore
    public void setEoBaInfo(List<SaveMODevInfoReqBD00EoBaInfo> eoBaInfo) {
        EoBaInfo = eoBaInfo;
    }
}
