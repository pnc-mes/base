package pnc.mesadmin.dto.GetAllBHChgInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhaochao on 11/20 0020.
 */
public class GetAllBHChgInfoReqBD00 {

    @JsonProperty("BomRd")
    private int BomRd;

    @JsonIgnore
    public int getBomRd() {
        return BomRd;
    }

    @JsonIgnore
    public void setBomRd(int bomRd) {
        BomRd = bomRd;
    }
}
