package pnc.mesadmin.dto.GetWfVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/14.
 */
public class GetWfVTreeInfoReqBD00 {

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
