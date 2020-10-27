package pnc.mesadmin.dto.GetMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/11/30.
 */
public class GetMWFInfoResDEndDC implements Serializable {
    @JsonProperty("DCVerRd")
    private int DCVerRd;

    @JsonProperty("DCName")
    private String DCName;

    @JsonIgnore
    public int getDCVerRd() {
        return DCVerRd;
    }

    @JsonIgnore
    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }

    @JsonIgnore
    public String getDCName() {
        return DCName;
    }

    @JsonIgnore
    public void setDCName(String DCName) {
        this.DCName = DCName;
    }
}
