package pnc.mesadmin.dto.SaveSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2017/8/16.
 */
public class SaveSGInfoReq01 implements Serializable {
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
