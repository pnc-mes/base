package pnc.mesadmin.dto.SaveRMInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/22.
 */
public class SaveRMInfoReqBD01 implements Serializable {

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
