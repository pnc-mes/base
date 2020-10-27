package pnc.mesadmin.dto.GetWfVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetWfVInfoReqBD01 implements Serializable{

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }

    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }
}
