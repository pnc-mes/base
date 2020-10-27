package pnc.mesadmin.dto.GetAllWfInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/7/13.
 */
public class GetAllRes implements Serializable {

    @JsonProperty("WfVerRd")
    private int WfVerRd;

    @JsonProperty("WfVerName")
    private String WfVerName;

    @JsonIgnore
    public int getWfVerRd() {
        return WfVerRd;
    }

    @JsonIgnore
    public void setWfVerRd(int wfVerRd) {
        WfVerRd = wfVerRd;
    }

    @JsonIgnore
    public String getWfVerName() {
        return WfVerName;
    }

    @JsonIgnore
    public void setWfVerName(String wfVerName) {
        WfVerName = wfVerName;
    }
}
