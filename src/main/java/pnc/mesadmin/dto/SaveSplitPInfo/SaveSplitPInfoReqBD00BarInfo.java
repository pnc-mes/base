package pnc.mesadmin.dto.SaveSplitPInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by test on 2017/9/27.
 */
public class SaveSplitPInfoReqBD00BarInfo implements Serializable{
    @JsonProperty("RelRd")
    private int RelRd;
    @JsonIgnore
    public int getRelRd() {
        return RelRd;
    }
    @JsonIgnore
    public void setRelRd(int relRd) {
        RelRd = relRd;
    }
}
