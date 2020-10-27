package pnc.mesadmin.dto.SaveMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class SaveMaInfoReqBD01 implements Serializable{

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
