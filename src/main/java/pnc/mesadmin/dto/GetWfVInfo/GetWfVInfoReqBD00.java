package pnc.mesadmin.dto.GetWfVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/9.
 */
public class GetWfVInfoReqBD00 implements Serializable{

    @JsonProperty("WfRd")
    private int WfRd;

    @JsonIgnore
    public int getWfRd() {
        return WfRd;
    }

    @JsonIgnore
    public void setWfRd(int wfRd) {
        WfRd = wfRd;
    }
}
