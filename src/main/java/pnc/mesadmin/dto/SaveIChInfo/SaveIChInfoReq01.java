package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/7.
 */
public class SaveIChInfoReq01 {
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
