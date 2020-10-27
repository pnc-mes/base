package pnc.mesadmin.dto.GetPdReGpInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/6.
 */
public class GetPdReGpInfoReqBD00 implements Serializable{

    @JsonProperty("MaRd")
    private int MaRd;

    @JsonIgnore
    public int getMaRd() {
        return MaRd;
    }

    @JsonIgnore
    public void setMaRd(int maRd) {
        MaRd = maRd;
    }
}
