package pnc.mesadmin.dto.SaveRMInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liufuzhi on 2018/1/28.
 */
public class SaveRMInfoReqBD04 {
    @JsonProperty("RetRd")
    private int RetRd;

    @JsonIgnore
    public int getRetRd() {
        return RetRd;
    }

    @JsonIgnore
    public void setRetRd(int retRd) {
        RetRd = retRd;
    }
}
