package pnc.mesadmin.dto.SaveIChInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by PNC on 2017/8/24.
 */
public class SaveIChInfoResD {
    @JsonProperty("InCRd")
    private int InCRd;

    @JsonProperty("InCCode")
    private String InCCode;

    @JsonIgnore
    public int getInCRd() {
        return InCRd;
    }

    @JsonIgnore
    public void setInCRd(int inCRd) {
        InCRd = inCRd;
    }

    @JsonIgnore
    public String getInCCode() {
        return InCCode;
    }

    @JsonIgnore
    public void setInCCode(String inCCode) {
        InCCode = inCCode;
    }
}
