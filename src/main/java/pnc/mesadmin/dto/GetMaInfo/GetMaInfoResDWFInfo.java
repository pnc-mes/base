package pnc.mesadmin.dto.GetMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/1.
 */
public class GetMaInfoResDWFInfo implements Serializable{

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("WFName")
    private String WFName;

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
