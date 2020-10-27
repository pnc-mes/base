package pnc.mesadmin.dto.GetBVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/8.
 */
public class GetBVInfoReqBD01 implements Serializable{

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
