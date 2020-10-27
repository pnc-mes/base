package pnc.mesadmin.dto.GetPdBOMDIInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/7.
 */
public class GetPdBOMDIInfoReqBD00 implements Serializable{

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }
}
