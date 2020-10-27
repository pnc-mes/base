package pnc.mesadmin.dto.SaveCMethodInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class SaveCMethodInfoReqBD01 implements Serializable {
    @JsonProperty("CMethodRd")
    private int CMethodRd;

    @JsonIgnore
    public int getCMethodRd() {
        return CMethodRd;
    }
    @JsonIgnore
    public void setCMethodRd(int CMethodRd) {
        this.CMethodRd = CMethodRd;
    }
}
