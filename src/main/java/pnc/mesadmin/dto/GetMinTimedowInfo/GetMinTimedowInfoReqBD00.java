package pnc.mesadmin.dto.GetMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class GetMinTimedowInfoReqBD00 implements Serializable{


    @JsonProperty("MinTimeWindowRd")
    private int MinTimeWindowRd;

    @JsonIgnore
    public int getMinTimeWindowRd() {
        return MinTimeWindowRd;
    }
    @JsonIgnore
    public void setMinTimeWindowRd(int minTimeWindowRd) {
        MinTimeWindowRd = minTimeWindowRd;
    }
}
