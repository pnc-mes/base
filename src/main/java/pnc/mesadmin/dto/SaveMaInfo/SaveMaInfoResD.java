package pnc.mesadmin.dto.SaveMaInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/6/1.
 */
public class SaveMaInfoResD {
    @JsonProperty("MaRd")
    private int MaRd;

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("MaCode")
    private String MaCode;

    @JsonIgnore
    public int getMaRd() {
        return MaRd;
    }

    @JsonIgnore
    public void setMaRd(int maRd) {
        MaRd = maRd;
    }

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public String getMaCode() {
        return MaCode;
    }

    @JsonIgnore
    public void setMaCode(String maCode) {
        MaCode = maCode;
    }
}
