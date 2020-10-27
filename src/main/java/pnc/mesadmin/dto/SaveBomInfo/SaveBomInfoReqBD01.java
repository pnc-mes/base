package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
/**
 * Created by xfxi on 2017/6/8.
 */
public class SaveBomInfoReqBD01 implements Serializable{

    @JsonProperty("BomRd")
    private int BomRd;

    @JsonIgnore
    public int getBomRd() {
        return BomRd;
    }

    @JsonIgnore
    public void setBomRd(int bomRd) {
        BomRd = bomRd;
    }
}
