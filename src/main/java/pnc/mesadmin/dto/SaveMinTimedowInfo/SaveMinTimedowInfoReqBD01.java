package pnc.mesadmin.dto.SaveMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class SaveMinTimedowInfoReqBD01 implements Serializable {
    @JsonProperty("MinTimedowInfoRd")
    private int MinTimedowInfoRd;

    @JsonIgnore
    public int getMinTimedowInfoRd() {
        return MinTimedowInfoRd;
    }
    @JsonIgnore
    public void setMinTimedowInfoRd(int minTimedowInfoRd) {
        MinTimedowInfoRd = minTimedowInfoRd;
    }
}
