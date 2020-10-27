package pnc.mesadmin.dto.GetAllSgyInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liufuzhi on 2018/1/30.
 */
public class GetAllSgyInfoResD {
    @JsonProperty("SgyRd")
    private int SgyRd;

    @JsonProperty("SgyName")
    private String SgyName;

    @JsonIgnore
    public int getSgyRd() {
        return SgyRd;
    }

    @JsonIgnore
    public void setSgyRd(int sgyRd) {
        SgyRd = sgyRd;
    }

    @JsonIgnore
    public String getSgyName() {
        return SgyName;
    }

    @JsonIgnore
    public void setSgyName(String sgyName) {
        SgyName = sgyName;
    }
}
