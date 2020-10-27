package pnc.mesadmin.dto.SaveMinTimedowInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2018/7/30.
 */
public class SaveMinTimedowInfoResD implements Serializable {

    @JsonProperty("MinTimeWindowRd")
    private int MinTimeWindowRd;

    @JsonProperty("MinTimeWindowName")
    private String MinTimeWindowName;

    @JsonIgnore

    public int getMinTimeWindowRd() {
        return MinTimeWindowRd;
    }
    @JsonIgnore
    public void setMinTimeWindowRd(int minTimeWindowRd) {
        MinTimeWindowRd = minTimeWindowRd;
    }
    @JsonIgnore
    public String getMinTimeWindowName() {
        return MinTimeWindowName;
    }
    @JsonIgnore
    public void setMinTimeWindowName(String minTimeWindowName) {
        MinTimeWindowName = minTimeWindowName;
    }
}
