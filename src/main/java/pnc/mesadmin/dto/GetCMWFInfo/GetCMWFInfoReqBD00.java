package pnc.mesadmin.dto.GetCMWFInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/6/28.
 */
public class GetCMWFInfoReqBD00 implements Serializable{
    @JsonProperty("MVerRd")
    private int MVerRd;

    @JsonIgnore
    public int getMVerRd() {
        return MVerRd;
    }
    @JsonIgnore
    public void setMVerRd(int MVerRd) {
        this.MVerRd = MVerRd;
    }
}
