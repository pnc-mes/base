package pnc.mesadmin.dto.GetDevSMInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2017/9/14.
 */
public class GetDevSMInfoReqBD00  implements Serializable {
    @JsonProperty("DSMRd")
    private int DSMRd;

    @JsonIgnore
    public int getDSMRd() {
        return DSMRd;
    }

    @JsonIgnore
    public void setDSMRd(int DSMRd) {
        this.DSMRd = DSMRd;
    }
}
