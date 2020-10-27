package pnc.mesadmin.dto.GetSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/16.
 */
public class GetSGInfoReq00 {
    @JsonProperty("SGRd")
    private int SGRd;

    @JsonIgnore
    public int getSGRd() {
        return SGRd;
    }

    @JsonIgnore
    public void setSGRd(int SGRd) {
        this.SGRd = SGRd;
    }
}
