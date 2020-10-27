package pnc.mesadmin.dto.SaveSgyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public class SaveSgyInfoReqBD01 implements Serializable {
    @JsonProperty("SgyRd")
    private int SgyRd;

    @JsonIgnore
    public int getSgyRd() {
        return SgyRd;
    }
    @JsonIgnore
    public void setSgyRd(int sgyRd) {
        SgyRd = sgyRd;
    }
}
