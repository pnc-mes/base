package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/6/8.
 */
public class SaveBomInfoResD {
    @JsonProperty("BomRd")
    private int BomRd;

    @JsonProperty("BomCode")
    private String BomCode;

    @JsonIgnore
    public int getBomRd() {
        return BomRd;
    }

    @JsonIgnore
    public void setBomRd(int bomRd) {
        BomRd = bomRd;
    }

    @JsonIgnore
    public String getBomCode() {
        return BomCode;
    }

    @JsonIgnore
    public void setBomCode(String bomCode) {
        BomCode = bomCode;
    }
}
