package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2018/1/28.
 */
public class SaveIChInfoReq04 {
    @JsonProperty("InCRd")
    private int InCRd;

    @JsonIgnore
    public int getInCRd() {
        return InCRd;
    }

    @JsonIgnore
    public void setInCRd(int inCRd) {
        InCRd = inCRd;
    }
}
