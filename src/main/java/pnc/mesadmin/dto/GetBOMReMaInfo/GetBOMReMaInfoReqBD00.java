package pnc.mesadmin.dto.GetBOMReMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetBOMReMaInfoReqBD00 implements Serializable{

    @JsonProperty("BomMaRd")
    private int BomMaRd;

    @JsonIgnore
    public int getBomMaRd() {
        return BomMaRd;
    }

    @JsonIgnore
    public void setBomMaRd(int bomMaRd) {
        BomMaRd = bomMaRd;
    }
}
