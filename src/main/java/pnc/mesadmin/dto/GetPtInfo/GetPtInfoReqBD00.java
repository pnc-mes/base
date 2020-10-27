package pnc.mesadmin.dto.GetPtInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/5/27.
 */
public class GetPtInfoReqBD00 implements Serializable{

    @JsonProperty("PtRd")
    private int PtRd;

    @JsonIgnore
    public int getPtRd() {
        return PtRd;
    }

    @JsonIgnore
    public void setPtRd(int ptRd) {
        PtRd = ptRd;
    }
}
