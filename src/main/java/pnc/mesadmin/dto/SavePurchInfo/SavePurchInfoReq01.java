package pnc.mesadmin.dto.SavePurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/6.
 */
public class SavePurchInfoReq01 {
    @JsonProperty("PurChRd")
    private int PurChRd;
    @JsonIgnore
    public int getPurChRd() {
        return PurChRd;
    }
    @JsonIgnore
    public void setPurChRd(int purChRd) {
        PurChRd = purChRd;
    }
}
