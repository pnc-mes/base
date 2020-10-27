package pnc.mesadmin.dto.GetCKDlInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/9.
 */
public class GetCKDlInfoReq00 {

    @JsonProperty("CkRd")
    private int CkRd;
    @JsonIgnore
    public int getCkRd() {
        return CkRd;
    }
    @JsonIgnore
    public void setCkRd(int ckRd) {
        CkRd = ckRd;
    }
}
