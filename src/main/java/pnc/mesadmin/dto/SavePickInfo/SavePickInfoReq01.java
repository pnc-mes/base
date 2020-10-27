package pnc.mesadmin.dto.SavePickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/13.
 */
public class SavePickInfoReq01 {
    @JsonProperty("PickRd")
    private int PickRd;
    @JsonIgnore
    public int getPickRd() {
        return PickRd;
    }
    @JsonIgnore
    public void setPickRd(int pickRd) {
        PickRd = pickRd;
    }
}
