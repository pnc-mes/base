package pnc.mesadmin.dto.SaveIOSInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/9/27.
 */
public class SaveIOSInfoReqBD04 {

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("TarScVRd")
    private int TarScVRd;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public int getTarScVRd() {
        return TarScVRd;
    }

    @JsonIgnore
    public void setTarScVRd(int tarScVRd) {
        TarScVRd = tarScVRd;
    }
}
