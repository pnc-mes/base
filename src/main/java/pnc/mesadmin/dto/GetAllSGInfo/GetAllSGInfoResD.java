package pnc.mesadmin.dto.GetAllSGInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/16.
 */
public class GetAllSGInfoResD {
    @JsonProperty("SGRd")
    private int SGRd;

    @JsonProperty("SGName")
    private String SGName;

    @JsonIgnore
    public int getSGRd() {
        return SGRd;
    }

    @JsonIgnore
    public void setSGRd(int SGRd) {
        this.SGRd = SGRd;
    }

    @JsonIgnore
    public String getSGName() {
        return SGName;
    }

    @JsonIgnore
    public void setSGName(String SGName) {
        this.SGName = SGName;
    }
}
