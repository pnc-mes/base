package pnc.mesadmin.dto.GetCLevelInfoRes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by PNC on 2019/3/22.
 */
public class GetCLevelInfoReqBD00 implements Serializable {

    @JsonProperty("CLevelRd")
    private int CLevelRd;

    @JsonIgnore
    public int getCLevelRd() {
        return CLevelRd;
    }
    @JsonIgnore
    public void setCLevelRd(int CLevelRd) {
        this.CLevelRd = CLevelRd;
    }
}
