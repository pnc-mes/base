package pnc.mesadmin.dto.SavePurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liufuzhi on 2018/1/28.
 */
public class SavePurchInfoReq04 {
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
