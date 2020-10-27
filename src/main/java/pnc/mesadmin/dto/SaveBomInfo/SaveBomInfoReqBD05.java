package pnc.mesadmin.dto.SaveBomInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/9.
 */
public class SaveBomInfoReqBD05 implements Serializable{

    @JsonProperty("BomVerRd")
    private int BomVerRd;

    @JsonIgnore
    public int getBomVerRd() {
        return BomVerRd;
    }

    @JsonIgnore
    public void setBomVerRd(int bomVerRd) {
        BomVerRd = bomVerRd;
    }
}
