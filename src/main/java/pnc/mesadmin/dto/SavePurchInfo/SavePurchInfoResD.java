package pnc.mesadmin.dto.SavePurchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/23.
 */
public class SavePurchInfoResD {
    @JsonProperty("PurChRd")
    private int PurChRd;

    @JsonProperty("PurChCode")
    private String PurChCode;

    @JsonIgnore
    public int getPurChRd() {
        return PurChRd;
    }

    @JsonIgnore
    public void setPurChRd(int purChRd) {
        PurChRd = purChRd;
    }

    @JsonIgnore
    public String getPurChCode() {
        return PurChCode;
    }

    @JsonIgnore
    public void setPurChCode(String purChCode) {
        PurChCode = purChCode;
    }
}
