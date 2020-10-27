package pnc.mesadmin.dto.GetIncInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/9/7.
 */
public class GetIncInfoReq00 {
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
