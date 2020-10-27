package pnc.mesadmin.dto.SavePickInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xf on 2018/1/31.
 */
public class SavePickInfoReq04 {
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
