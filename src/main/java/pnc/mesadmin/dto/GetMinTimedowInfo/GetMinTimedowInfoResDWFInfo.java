package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoResDWFInfo implements Serializable {
    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("WFName")
    private String  WFName;
    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }
    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }
    @JsonIgnore
    public String getWFName() {
        return WFName;
    }
    @JsonIgnore
    public void setWFName(String WFName) {
        this.WFName = WFName;
    }
}
