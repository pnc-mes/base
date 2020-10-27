package pnc.mesadmin.dto.GetMODevInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/8/29.
 */
public class GetMODevInfoReqBD00 {

    @JsonProperty("Batch")
    private String Batch;

    @JsonProperty("SpecVerRd")
    private int SpecVerRd;

    @JsonProperty("DevRd")
    private int DevRd;

    @JsonIgnore
    public String getBatch() {
        return Batch;
    }

    @JsonIgnore
    public void setBatch(String batch) {
        Batch = batch;
    }

    @JsonIgnore
    public int getSpecVerRd() {
        return SpecVerRd;
    }

    @JsonIgnore
    public void setSpecVerRd(int specVerRd) {
        SpecVerRd = specVerRd;
    }

    @JsonIgnore
    public int getDevRd() {
        return DevRd;
    }

    @JsonIgnore
    public void setDevRd(int devRd) {
        DevRd = devRd;
    }
}
