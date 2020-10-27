package pnc.mesadmin.dto.GetMVTreeInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by xfxi on 2017/6/3.
 */
public class GetMVTreeInfoReqBD00 implements Serializable {

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
