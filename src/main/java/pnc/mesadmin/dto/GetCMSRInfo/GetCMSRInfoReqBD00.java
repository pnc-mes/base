package pnc.mesadmin.dto.GetCMSRInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xfxi on 2017/7/10.
 */
public class GetCMSRInfoReqBD00 {

    @JsonProperty("MaVerRd")
    private int MaVerRd;

    @JsonProperty("Count")
    private int Count;

    @JsonIgnore
    public int getMaVerRd() {
        return MaVerRd;
    }

    @JsonIgnore
    public void setMaVerRd(int maVerRd) {
        MaVerRd = maVerRd;
    }

    @JsonIgnore
    public int getCount() {
        return Count;
    }

    @JsonIgnore
    public void setCount(int count) {
        Count = count;
    }
}
