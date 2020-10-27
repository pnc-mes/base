package pnc.mesadmin.dto.SaveWOInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/10/22.
 */
public class SaveWOInfoReqBD05 {

    @JsonProperty("WoRd")
    private int WoRd;

    @JsonIgnore
    public int getWoRd() {
        return WoRd;
    }

    @JsonIgnore
    public void setWoRd(int woRd) {
        WoRd = woRd;
    }
}
