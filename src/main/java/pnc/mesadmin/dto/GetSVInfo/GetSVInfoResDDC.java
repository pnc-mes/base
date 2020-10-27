package pnc.mesadmin.dto.GetSVInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/22.
 */
public class GetSVInfoResDDC {
    @JsonProperty("DCVerRd")
    private int DCVerRd;
    @JsonProperty("DCName")
    private String DCName;
    @JsonIgnore
    public int getDCVerRd() {
        return DCVerRd;
    }
    @JsonIgnore
    public void setDCVerRd(int DCVerRd) {
        this.DCVerRd = DCVerRd;
    }
    @JsonIgnore
    public String getDCName() {
        return DCName;
    }
    @JsonIgnore
    public void setDCName(String DCName) {
        this.DCName = DCName;
    }
}
