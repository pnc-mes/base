package pnc.mesadmin.dto.SaveBatchInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/6/10.
 */
public class SaveBatchInfoReqBD01 {
    @JsonProperty("BRd")
    private int BRd;
    @JsonIgnore
    public int getBRd() {
        return BRd;
    }
    @JsonIgnore
    public void setBRd(int BRd) {
        this.BRd = BRd;
    }
}
