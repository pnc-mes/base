package pnc.mesadmin.dto.GetSgyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public class GetSgyInfoReqBD00 {
    @JsonProperty("SgyRd")
    private int SgyRd;

    @JsonIgnore
    public int getSgyRd() {
        return SgyRd;
    }

    @JsonIgnore
    public void setSgyRd(int sgyRd) {
        SgyRd = sgyRd;
    }
}
