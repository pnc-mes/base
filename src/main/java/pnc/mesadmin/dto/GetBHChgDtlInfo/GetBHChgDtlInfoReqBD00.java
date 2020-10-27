package pnc.mesadmin.dto.GetBHChgDtlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetBHChgDtlInfoReqBD00 {

    @JsonProperty("BomChgRd")
    private int BomChgRd;

    @JsonIgnore
    public int getBomChgRd() {
        return BomChgRd;
    }

    @JsonIgnore
    public void setBomChgRd(int bomChgRd) {
        BomChgRd = bomChgRd;
    }
}
